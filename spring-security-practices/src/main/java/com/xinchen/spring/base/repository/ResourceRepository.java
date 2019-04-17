package com.xinchen.spring.base.repository;

import com.xinchen.spring.base.entity.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xinchen
 * @version 1.0
 * @date 17/04/2019 16:56
 */
public interface ResourceRepository extends CrudRepository<Resource,Long> {
}
