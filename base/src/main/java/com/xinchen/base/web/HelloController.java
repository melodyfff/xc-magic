package com.xinchen.base.web;

import com.xinchen.base.core.entity.User;
import com.xinchen.base.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/1 0:12
 */
@Controller
public class HelloController {

    @Resource
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/insert")
    @ResponseBody
    public void insert(){
        User user = new User();
        user.setId(1);
        user.setName("hello");
        userService.addUser(user);
    }

    @GetMapping("/get")
    @ResponseBody
    public User get(){
        return userService.findOne(1);
    }
}
