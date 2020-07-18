package com.kingcall.jpa;

import com.kingcall.jpa.entity.user.User;
import com.kingcall.jpa.dao.user.UserReposity;
import com.kingcall.jpa.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {


    Logger log = LoggerFactory.getLogger(JpaApplicationTests.class);
    @Autowired
    ApplicationContext ioc;
    @Autowired
    UserService userService;

    @MockBean
    UserReposity userdao;

    @Test
    public void addUser(){
        User user = new User();
        user.setDepartmentId(4);
        user.setName("你妹的2");
        user.setDepartmentId(101);
        user.setCreateTime(new Date());
        userService.addUser(user);
    }

    /**
     * Example 查询
     */
    @Test
    public void findAll(){
        User user = new User();
        user.setDepartmentId(4);
        user.setName("kingcall");
        Example<User> example = Example.of(user);
        List<User> users = userService.findAll(example);
        users.forEach(x-> System.out.println(x));
    }
    @Test
    public void findByName(){
        List<User> users = userService.findByName("kingcall");
        users.forEach(x-> System.out.println(x));
    }
    @Test
    public void findFirst3ByName(){
        List<User> users = userService.findFirst3ByName("kingcall");
        users.forEach(x-> System.out.println(x));
    }

    @Test
    public void findByNameAndId(){
        List<User> users = userService.findByNameAndId("kingcall",48);
        users.forEach(x-> System.out.println(x));
    }

    @Test
    public void findByNameOrId(){
        List<User> users = userService.findByNameOrId("kingcall",39);
        users.forEach(x-> System.out.println(x));
    }


    @Test
    public void findDistinctByName(){
        List<User> users = userService.findDistinctByName("kingcall");
        users.forEach(x-> System.out.println(x));
    }

    @Test
    public void findDistinctUserByName(){
        List<User> users = userService.findDistinctUserByName("kingcall");
        users.forEach(x-> System.out.println(x));
    }
    @Test
    public void findUsers(){
        List<User> users = userService.findUsers("kingcall");
        users.forEach(x-> System.out.println(x));
    }

    @Test
    public void findUsers2(){
        List<User> users = userService.findUsers2("kingcall");
        users.forEach(x-> System.out.println(x));
    }

    @Test
    public void findUsers3(){
        List<User> users = userService.findUsers3 ("kingcall",48);
        users.forEach(x-> System.out.println(x));
    }
    @Test
    public void findUsers4(){
        List<User> users = userService.findUsers4 ("kingcall",48);
        users.forEach(x-> System.out.println(x));
    }

    @Test
    public void departmentCount(){
        List<Object[]> users = userService.departmentCount ();
        users.forEach(x-> System.out.println(x[0]+"\t"+x[1]));
    }
    @Test
    @Rollback(true)
    public void deleteAndInert(){
//        given(userdao.deleteAndInsert(anyInt())).willReturn("我假设你成功了");
        String result = userService.deleteAndInsert(10);
        System.out.println(result);
    }




    @Test
    public void contextLoads() {
        log.trace("这是trace 日志");
        log.info("这是info 日志");
        log.warn("这是 warn 日志");
        log.error("这是 error 日志");
        System.out.println(ioc.containsBean("Jpa_Userservice"));
    }

}
