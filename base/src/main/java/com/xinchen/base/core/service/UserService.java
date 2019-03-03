package com.xinchen.base.core.service;

import com.xinchen.base.core.entity.User;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/3 23:02
 */
public interface UserService {

    User findOne(long id);

    void addUser(User user);
}
