package com.xinchen.base.core.repository;


import com.xinchen.base.ApplicationMockTests;
import com.xinchen.base.core.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

/**
 * @author xinchen
 * @version 1.0
 * @date 04/03/2019 14:20
 */
public class UserRepositoryMockTest extends ApplicationMockTests {

    private UserRepository userRepository;

    private User user;

    @Before
    public void init(){
        userRepository = mock(UserRepository.class);
        user = mock(User.class);
    }

    @Test
    public void insert(){
        userRepository.save(user);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void get(){
        userRepository.findAll();
        Mockito.verify(userRepository).findAll();
    }
}