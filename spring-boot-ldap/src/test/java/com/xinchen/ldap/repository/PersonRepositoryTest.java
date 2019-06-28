package com.xinchen.ldap.repository;

import com.xinchen.ldap.LdapApplicationTests;
import com.xinchen.ldap.entity.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author xinchen
 * @version 1.0
 * @date 28/06/2019 15:03
 */
public class PersonRepositoryTest extends LdapApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void queryAll(){
        final Iterable<Person> all = personRepository.findAll();
        System.out.println(all);
    }
}