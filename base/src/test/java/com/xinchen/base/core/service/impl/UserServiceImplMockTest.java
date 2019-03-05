package com.xinchen.base.core.service.impl;

import com.xinchen.base.ApplicationMockTests;
import com.xinchen.base.core.entity.User;
import com.xinchen.base.core.repository.UserRepository;
import com.xinchen.base.core.service.UserService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * @author xinchen
 * @version 1.0
 * @date 05/03/2019 10:45
 */
public class UserServiceImplMockTest extends ApplicationMockTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Test
    public void findOne() {
        userService.findOne(Mockito.anyLong());
        Mockito.verify(userRepository).getOne(Mockito.anyLong());
    }

    @Test
    public void addUser() {
        userService.addUser(user);
        Mockito.verify(userRepository).save(user);
    }
}