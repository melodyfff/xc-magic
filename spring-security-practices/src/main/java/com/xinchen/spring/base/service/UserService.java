package com.xinchen.spring.base.service;

import com.xinchen.spring.base.entity.User;

import java.util.Optional;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/15 23:16
 */
public interface UserService {
    void insertUser(User user) throws Exception;

    Optional<User> getUserById(long id);
}
