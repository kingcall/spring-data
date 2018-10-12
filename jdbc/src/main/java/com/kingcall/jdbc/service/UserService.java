package com.kingcall.jdbc.service;


import com.kingcall.jdbc.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
	public User geUserById(Integer id) ;
	public Map geUserByIdForMap(Integer id) ;
	public Integer getUserCount() ;
	public List<User> getUserByDepartmentId(Integer id) ;
	public int updateUser(User user) ;
	public int updateUser2(User user) ;
	public int insertUser(User user);
	public int insertUser2(User user);
	public int insertUser3(User user);

}
