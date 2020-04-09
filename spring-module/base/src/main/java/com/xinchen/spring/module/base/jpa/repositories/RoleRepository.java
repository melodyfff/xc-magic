package com.xinchen.spring.module.base.jpa.repositories;

import com.xinchen.spring.module.base.jpa.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xinchen
 * @version 1.0
 * @date 14/10/2019 11:09
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
