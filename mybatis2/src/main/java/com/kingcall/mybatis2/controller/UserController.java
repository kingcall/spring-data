package com.kingcall.mybatis2.controller;

import com.kingcall.mybatis2.mapper.UserMapper;
import com.kingcall.mybatis2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @PostMapping
    public int create(@RequestBody User user) {
        return userMapper.insert(user);
    }

    @PutMapping
    public int update(@RequestBody User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

}
