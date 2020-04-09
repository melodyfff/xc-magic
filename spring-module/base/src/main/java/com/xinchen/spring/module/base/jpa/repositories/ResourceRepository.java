package com.xinchen.spring.module.base.jpa.repositories;

import com.xinchen.spring.module.base.jpa.domain.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xinchen
 * @version 1.0
 * @date 14/10/2019 11:09
 */
public interface ResourceRepository extends CrudRepository<Resource, Integer> {
}
