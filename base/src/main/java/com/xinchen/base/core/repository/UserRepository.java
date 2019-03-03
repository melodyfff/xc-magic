package com.xinchen.base.core.repository;

import com.xinchen.base.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/3 22:14
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
