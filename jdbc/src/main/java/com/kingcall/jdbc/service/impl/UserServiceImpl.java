package com.kingcall.jdbc.service.impl;

import com.kingcall.jdbc.dao.UserDao;
import com.kingcall.jdbc.entity.User;
import com.kingcall.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("database_UserServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public User geUserById(Integer id) {
		User user = userDao.findUserById(id);
		return user;
	}

    @Override
    public Map geUserByIdForMap(Integer id) {
        Map user = userDao.findUserById2(id);
        return user;
    }

	@Override
	public Integer getUserCount() {
		return userDao.userCount();
	}

	@Override
	public List<User> getUserByDepartmentId(Integer id) {
		return userDao.getUserByDepartmentId(id);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateInfo(user);
	}

	@Override
	public int updateUser2(User user) {
		return userDao.updateInfoByNamedJdbc(user);
	}

	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public int insertUser2(User user) {
		return userDao.insertUser2(user);
	}

	@Override
	public int insertUser3(User user) {
		return userDao.insertUser3(user);
	}
}
