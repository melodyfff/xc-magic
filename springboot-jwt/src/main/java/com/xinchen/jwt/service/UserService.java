package com.xinchen.jwt.service;

import com.xinchen.jwt.entity.User;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/5/28 23:50
 */
@FunctionalInterface
public interface UserService {

    /**
     * 请求一个user
     * @param user user
     * @return User
     */
    User call(User user);

    /**
     * 返回一个User
     *
     * 默认返回 admin:admin
     *
     * @param u u
     * @return u
     */
    static UserService getUser(User u){
        return user -> null == u ? new User("1","admin","admin") : u;
    }
}
