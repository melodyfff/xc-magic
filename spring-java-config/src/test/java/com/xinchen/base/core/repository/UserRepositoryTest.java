package com.xinchen.base.core.repository;


import com.xinchen.base.ApplicationContextTests;
import com.xinchen.base.core.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author xinchen
 * @version 1.0
 * @date 04/03/2019 14:20
 */
public class UserRepositoryTest extends ApplicationContextTests {

    @Resource
    private UserRepository userRepository;

    private User user;

    @Before
    public void init(){
        user = new User();
        user.setId(1);
        user.setName("Hello");
    }

    @Test
    public void insert(){
        Assert.assertEquals(userRepository.save(user),user);
    }

    @Test
    public void get(){
        Assert.assertNotNull(userRepository.findAll());
    }
}