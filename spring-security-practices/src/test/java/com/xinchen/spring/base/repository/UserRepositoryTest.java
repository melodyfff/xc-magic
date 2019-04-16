package com.xinchen.spring.base.repository;

import com.xinchen.spring.AppTest;
import com.xinchen.spring.base.entity.Role;
import com.xinchen.spring.base.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/15 22:36
 */
public class UserRepositoryTest extends AppTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testInsert(){

        final Role role = roleRepository.findById(2L).get();

        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }

    @Test
    public void testDelete(){
        final User user = userRepository.findById(3L).get();


        userRepository.delete(user);
    }

}