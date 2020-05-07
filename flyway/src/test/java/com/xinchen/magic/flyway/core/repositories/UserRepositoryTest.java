package com.xinchen.magic.flyway.core.repositories;


import com.xinchen.magic.flyway.FlywayApplicationTests;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author xinchen
 * @version 1.0
 * @date 27/04/2020 16:24
 */
public class UserRepositoryTest extends FlywayApplicationTests {
    @Resource
    private UserRepository repository;

    @Test
    public void test(){
        System.out.println(repository.findAll());
    }

}