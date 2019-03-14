package com.xinchen.base.core.service.impl;

import com.xinchen.base.ApplicationContextTests;
import com.xinchen.base.core.entity.User;
import com.xinchen.base.core.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author xinchen
 * @version 1.0
 * @date 05/03/2019 10:51
 */
public class UserServiceImplTest extends ApplicationContextTests {

    @Resource
    private UserService userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setId(1);
        user.setName("hello");
    }

    @Test
    public void findOne() {
        Assert.assertEquals(userService.findOne(1),user);
    }

    @Test
    public void addUser() {
        userService.addUser(user);
    }
}