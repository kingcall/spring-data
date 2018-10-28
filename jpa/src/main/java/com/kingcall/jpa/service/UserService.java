package com.kingcall.jpa.service;

import com.kingcall.jpa.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

;

public interface UserService {
    public Integer addUser(User user);
    public  List<User> addUser(List<User> users);
    public List<User> findAll();
    public List<User> findAll(Example example);
    public List<User> findAllAndSort(Sort sort);
    public Page<User> findAllAndPage(Pageable pageable);
    public User getOne(Integer id);
    public Integer count();
    public void deleteById(Integer id);
    public void delete(User user);


    List<User> findUserByNameOrderByIdDesc(String name);
    List<User> findUserByNameOrderByIdAsc(String name);
    List<User> findByName(String name);
    List<User> findFirst3ByName(String name);
    List<User> findByNameAndId(String name, Integer id);
    List<User> findByNameOrId(String name, Integer id);
    List<User> findDistinctByName(String name);
    List<User> findDistinctUserByName(String name);


    public List<User> findUsers(String name);
    public List<User> findUsers2(String name);
    public List<User> findUsers3(String name, Integer id);
    public List<User> findUsers4(String name, Integer id);
    public List<Object[]> departmentCount();
    public String deleteAndInsert(Integer id);



}
