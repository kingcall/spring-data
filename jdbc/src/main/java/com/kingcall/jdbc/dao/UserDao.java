package com.kingcall.jdbc.dao;

import com.kingcall.jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate jdbcTempalte;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Integer totalUserInDepartment(Long departmentId) {
		String sql = "select count(1) from user where department_id=?";
		Integer count = jdbcTempalte.queryForObject(sql, Integer.class, departmentId);
		return count;
	}
	
	public Integer totalUserInDepartment2(Long departmentId) {
		String sql = "select count(1) from user where department_id=:deptId";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    namedParameters.addValue("deptId", departmentId);
		Integer count = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,  Integer.class) ;
		return count;
	}

    /**
     * Incorrect result size: expected 1, actual 0 存在这样的错误
     * @param userId
     * @return
     */
	public User findUserById(Integer userId) {
		String sql = "select * from user where id=?";
		User user = jdbcTempalte.queryForObject(sql,new UserRowMapper(), userId);
		return user;
	}

    /**
     * 还是存在这样的问题  Incorrect result size: expected 1, actual 0
     * @param userId
     * @return
     */
	public Map findUserById2(Integer userId) {
		String sql = "select * from user where id=?";
		Map map = jdbcTempalte.queryForMap(sql, userId);
		return map;
	}

	/**
	 * 查询用户数量
	 * @return 用户数量
	 */
	public Integer userCount() {
		String sql = "select count(*) from user";
		Integer count = jdbcTempalte.queryForObject(sql, Integer.class);
		return count;
	}

    /**
     * query 方法返回的是用户列表
     * @param departmenetId
     * @return
     */
	public List<User> getUserByDepartmentId(Integer departmenetId) {
		String sql = "select * from user where department_id=? ";
		List<User> users = jdbcTempalte.query(sql, new UserRowMapper(), departmenetId);
		return users;
	}

	public int updateInfo(User user) {
		String sql = "update user set name=? ,department_id=? where id = ?";
		int result=jdbcTempalte.update(sql, user.getName(), user.getDepartmentId(), user.getId());
        return result;
	}

	/**
	 * JdbcTemplate 的增强，JdbcTemplate 只支持 ？ 占位符，但是 NamedParameterJdbcTemplate 支持使用参数的名字
     * SqlParameterSource 更加高级的参数传递对象
	 * @param user
	 */
	public int updateInfoByNamedJdbc(User user) {
		String sql = "update user set name=:name and departmet_id=:departmentId where id = :id";
		SqlParameterSource source = new BeanPropertySqlParameterSource(user);
		return namedParameterJdbcTemplate.update(sql, source);
	}

    /**
     * 对含有自增键的表插入(失败了)
     * @param user
     * @return
     */
	public Integer insertUser(final User user) {
		final String sql = "insert into user (name, department_id ) values (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempalte.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, user.getName());
				ps.setInt(2, user.getDepartmentId());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

    /**
     *  普通方式的插入(发现对于自增主键我们可以不用处理)
     * @param user
     * @return
     */
    public Integer insertUser2(final User user) {
        final String sql = "insert into user (name, department_id ) values (?,?)";
        return jdbcTempalte.update(sql, user.getName(), user.getDepartmentId());
    }

    /**
     * 增强插入的使用
     *      BeanPropertySqlParameterSource  对象赋值给 MapSqlParameterSource 更方便
     * @param user
     * @return
     */
    public Integer insertUser3(final User user) {
        final String sql = "insert into user (name, department_id ) values (:name,:department_id)";
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", user.getName());
        source.addValue("department_id", user.getDepartmentId());
        return jdbcTempalte.update(sql,source,Integer.class);
    }

	static class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setDepartmentId(rs.getInt("department_id"));
			return user;
		}
	}
}