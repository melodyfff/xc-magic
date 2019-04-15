package com.xinchen.spring.base.repository;

import com.xinchen.spring.base.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/15 22:25
 */
public interface UserRepository extends CrudRepository<User,Long> {
}
