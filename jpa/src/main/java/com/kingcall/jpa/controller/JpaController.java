package com.kingcall.jpa.controller;

import com.kingcall.integration.database.mysql.jdbc.entity.User;
import com.kingcall.integration.database.mysql.jpa.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 为了学习测试方便，直接将dao 接口注入进来使用—— 这是不符合规范的
 */
@Api(value = "JpaController", description = "Jpa 接口")
@RestController
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    @Qualifier("Jpa_Userservice")
    UserService userService;
    @RequestMapping(value = "/adduser")
    public String addUser(User user){
        System.out.println("待保存的用户信息:"+user.toString());
        Integer result = userService.addUser(user);
        System.out.println("插入用户的用户ID 是 "+result);
        return user.toString();
    }

    /**
     * 批量添加记录
     * @param user
     * @return
     */
    @RequestMapping(value = "/adduserall")
    public List<User> addUserAll(){
        List<User> users = new ArrayList<>(10);
        users.add(new User(10,"kingcall",new Date()));
        users.add(new User(10,"kingcall2",new Date()));
        users.add(new User(10,"kingcall3",new Date()));
        return userService.addUser(users);
    }
    @RequestMapping(value = "/adduserall2")
    public List<User> adduserall2(){
        List<User> users = new ArrayList<>(10);
        users.add(new User(1,10,"kingcall",new Date()));
        users.add(new User(2,10,"kingcall2",new Date()));
        users.add(new User(45,"kingcall3",new Date()));
        return userService.addUser(users);
    }

    @RequestMapping("/all")
    public List<User> findAll(){
        System.out.println("获取全部用户信息的方法被执行");
        return  userService.findAll();
    }

    /**
     * 在获取的同时进行排序
     *  可以根据多列同时进行排序，也可以指定
     * @return
     */
    @RequestMapping("/allsort")
    public List<User> allSort(){
        System.out.println("获取全部用户信息并进行排序的方法被执行");
        Sort sort = new Sort(Sort.Direction.DESC,"name");
        return  userService.findAllAndSort(sort);
    }

    @RequestMapping("/allpage/{id}")
    public List<User> allPage(@PathVariable int id){
        System.out.println("分页查询");
        Sort sort = new Sort(Sort.Direction.DESC,"name");
        PageRequest pageRequest = new PageRequest(id, 3, sort);
        Page<User> page= userService.findAllAndPage(pageRequest);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        return page.getContent();
    }

    @RequestMapping("/getone/{id}")
    public String getOne(@PathVariable Integer id){
        System.out.println("待获取用户的id :"+id);
        System.out.println(userService.getOne(id));
        return userService.getOne(id).toString();
    }
    @RequestMapping("/count")
    public Integer count(){
        return  userService.count();
    }
    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        userService.deleteById(id);
    }
    @RequestMapping("/deleteuser}")
    public void deleteuser(User user){
        userService.delete(user);
    }

    /**
     * 自定义方法查询
     * @return
     */
    @RequestMapping("/define/{name}")
    public List<User> findUserByNameOrderByIdDesc(@PathVariable String name){
        return userService.findUserByNameOrderByIdDesc(name);
    }
    @RequestMapping("/define2/{name}")
    public List<User> findUserByNameOrderByIdAsc(@PathVariable String name){
        return userService.findUserByNameOrderByIdAsc(name);
    }
    @RequestMapping("/user}")
    public List<User> findByNameAndId(@RequestParam(value = "name")String name, @RequestParam(value = "id") String id){
        System.out.println("根据用户名和用户id 获取用户信息的方法被执行");
        return userService.findByNameAndId(name,Integer.parseInt(id));
    }


}
