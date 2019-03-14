package com.xinchen.base.core.service.impl;

import com.xinchen.base.core.entity.User;
import com.xinchen.base.core.repository.UserRepository;
import com.xinchen.base.core.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/3 23:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository repository;

    @Override
    public User findOne(long id) {
        return repository.getOne(id);
    }

    @Override
    public void addUser(User user) {
        repository.save(user);
    }
}
