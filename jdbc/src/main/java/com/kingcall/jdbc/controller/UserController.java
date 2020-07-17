package com.kingcall.jdbc.controller;

import com.kingcall.jdbc.entity.User;
import com.kingcall.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class UserController {
	@Autowired
	@Qualifier("UserServiceImpl")
    UserService userService;
	@RequestMapping("/user/{id}")
	public @ResponseBody
	User say(@PathVariable Integer id){
        System.out.println("待查询人员id:"+id);
		User user= userService.geUserById(id);
		return user;
	}
    @RequestMapping("/user/map/{id}")
    public @ResponseBody
    Map say2(@PathVariable Integer id){
        System.out.println("map待查询人员id:"+id);
        Map user= userService.geUserByIdForMap(id);
        return user;
    }
	@RequestMapping("/user/all")
	public @ResponseBody Integer say3(){
		System.out.println("查询用户总数量");
		Integer count = userService.getUserCount();
		return count;
	}
    @RequestMapping("/user/list/{id}")
    public @ResponseBody List<User> say4(@PathVariable Integer id){
        System.out.println("查询特定部门的用户列表");
        List<User> users = userService.getUserByDepartmentId(id);
        return users;
    }

    /**
     * 更新数据库信息   将http的参数，自动映射到java对象上去
     * @param user
     * @return
     */
    @RequestMapping("/user/update")
    public @ResponseBody String say5(User user){
        System.out.println("待更新的用户信息是："+user.toString());
        if (userService.updateUser(user)>0) {
            return "sucess";
        }else {
            return "更新用户信息失败";
        }
    }

    /**
     *  使用增强版的jdbctemplate-> NamedParameterTemplate 修改记录
     * @param user
     * @return
     */
    @RequestMapping("/user/namedupdate")
    public @ResponseBody String say6(User user){
        System.out.println("待更新的用户信息是："+user.toString());
        if (userService.updateUser(user)>0) {
            return "sucess";
        }else {
            return "更新用户信息失败";
        }
    }

    /**
     * 含有主键的插入
     * @param user
     * @return
     */
    @RequestMapping("/user/insert")
    public @ResponseBody String say7(User user){
        System.out.println("待插入的用户信息是："+user.toString());
        if (userService.insertUser(user)>0) {
            return "插入用户信息成功";
        }else {
            return "插入用户信息失败";
        }
    }

    /**
     * 普通的方式插入
     * @param user
     * @return
     */
    @RequestMapping("/user/insert2")
    public @ResponseBody String say8(User user){
        System.out.println("待插入的用户信息2是："+user.toString());
        if (userService.insertUser2(user)>0) {
            return "插入用户信息成功";
        }else {
            return "插入用户信息失败";
        }
    }
    /**
     * 增强的方式插入
     * @param user
     * @return
     */
    @RequestMapping("/user/insert3")
    public @ResponseBody String say9(User user){
        System.out.println("待插入的用户信息3是："+user.toString());
        if (userService.insertUser3(user)>0) {
            return "插入用户信息成功";
        }else {
            return "插入用户信息失败";
        }
    }
}
