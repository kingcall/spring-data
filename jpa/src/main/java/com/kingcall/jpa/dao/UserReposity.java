package com.kingcall.jpa.dao;

import com.kingcall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserReposity extends JpaRepository<User,Integer>{
    /**
     *  下面的方法大都是自定义的
     * @param name
     * @return
     */
    List<User> findUserByNameOrderByIdDesc(String name);
    List<User> findUserByNameOrderByIdAsc(String name);
    List<User> findByName(String name);

    /**
     * 根据用户名获取前三个用户
     * @param name
     * @return
     */
    List<User> findFirst3ByName(String name);

    /**
     * 根据用户名和 id 查询
     * @param name
     * @param id
     * @return
     */
    List<User> findByNameAndId(String name, Integer id);
    List<User> findByNameOrId(String name, Integer id);
    List<User> findDistinctByName(String name);
    List<User> findDistinctUserByName(String name);

    /**
     * 使用标准的sql
     * @param name
     * @return
     */
    @Query(value = "select * from user where name=?1",nativeQuery = true)
    public List<User> findUsers(String name);

 /**
     * 使用 jpql
     * @param name
     * @return
     */
    @Query(value = "select u from User u where u.name=?1")
    public List<User> findUsers2(String name);

    /**
     * 使用命名参数
     * @param name
     * @return
     */
    @Query(value = "select * from user  where name=:name and id=:id",nativeQuery = true)
    public List<User> findUsers3(@Param("name") String name, @Param("id") Integer id);

    /**
     * 占位符的方式
     * @param name
     * @param id
     * @return
     */
    @Query(value = "select * from user  where name=?1 and id=?2",nativeQuery = true)
    public List<User> findUsers4(String name, Integer id);

    /**
     * 对于结果集并非 Entity 的，可以使用<Object[] 代替
     * @return
     */
    @Query(value = "select department_id,count(*) as cnt from user group by department_id",nativeQuery = true)
    public List<Object[]> departmentCount();


//    public String deleteAndInsert(Integer id);


}
