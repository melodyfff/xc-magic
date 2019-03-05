package com.xinchen.base.web;

import com.xinchen.base.core.entity.User;
import com.xinchen.base.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author xinchen
 * @version 1.0
 * @date 04/03/2019 13:32
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/insert/{id}/{name}")
    @ResponseBody
    public void insert(@PathVariable("id") long id,@PathVariable("name") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        userService.addUser(user);
    }

    @PostMapping("/insert")
    public void insert2(@ModelAttribute("id") long id, @ModelAttribute("name") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        userService.addUser(user);
    }

    @GetMapping(value = "/get/{id}")
    @ResponseBody()
    public User get(@PathVariable long id){
        return userService.findOne(id);
    }
}
