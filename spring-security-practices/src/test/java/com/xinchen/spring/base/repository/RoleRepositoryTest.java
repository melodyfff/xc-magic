package com.xinchen.spring.base.repository;

import com.xinchen.spring.AppTest;
import com.xinchen.spring.base.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/17 0:33
 */
public class RoleRepositoryTest extends AppTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testInsertRole(){
        Role role = new Role();
        role.setRoleName("系统管理员");
        roleRepository.save(role);
    }

    @Test
    public void testUpdateRole(){
        final Role role = roleRepository.findById(1L).get();
        role.setRoleName("超级系统管理员");
        roleRepository.save(role);
    }
}