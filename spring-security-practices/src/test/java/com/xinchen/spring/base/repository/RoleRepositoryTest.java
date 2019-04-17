package com.xinchen.spring.base.repository;

import com.xinchen.spring.AppTest;
import com.xinchen.spring.base.entity.Resource;
import com.xinchen.spring.base.entity.Role;
import org.assertj.core.util.Maps;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;


/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/17 0:33
 */
public class RoleRepositoryTest extends AppTest {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

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

    @Test
    public void insertResourceTest(){
        final Resource resource = resourceRepository.findById(1L).get();
        final Role role = roleRepository.findById(1L).get();
        role.setResources(Sets.newLinkedHashSet(resource));
        roleRepository.save(role);
    }
}