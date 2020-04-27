package com.xinchen.magic.flyway.core.repositories;

import com.xinchen.magic.flyway.core.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xinchen
 * @version 1.0
 * @date 27/04/2020 16:23
 */
public interface UserRepository extends CrudRepository<User,Integer> {
}
