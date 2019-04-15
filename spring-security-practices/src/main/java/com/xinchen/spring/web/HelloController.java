package com.xinchen.spring.web;

import com.xinchen.spring.base.entity.User;
import com.xinchen.spring.base.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/15 23:11
 */
@RestController
@RequestMapping("/user")
public class HelloController {

    @Resource
    private UserService userService;

    @GetMapping("/add/{userName}")
    public User insert(@PathVariable("userName") String userName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        userService.insertUser(user);

        return user;
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") Long id) throws Exception {
        return userService.getUserById(id).orElseThrow(Exception::new);
    }
}
