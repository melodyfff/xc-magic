package com.xinchen.ldap.repository;

import com.xinchen.ldap.entity.Person;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

/**
 * @author xinchen
 * @version 1.0
 * @date 28/06/2019 14:18
 */
public interface PersonRepository extends CrudRepository<Person, Name> {
}
