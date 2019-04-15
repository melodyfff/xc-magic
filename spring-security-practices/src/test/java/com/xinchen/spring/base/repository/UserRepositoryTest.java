package com.xinchen.spring.base.repository;

import com.xinchen.spring.AppTest;
import com.xinchen.spring.base.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/15 22:36
 */
public class UserRepositoryTest extends AppTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        User user = new User();
        user.setUserName("test");

        userRepository.save(user);

        System.out.println(userRepository.findById(1L).get());

    }

}