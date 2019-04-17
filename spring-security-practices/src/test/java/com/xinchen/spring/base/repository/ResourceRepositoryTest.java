package com.xinchen.spring.base.repository;


import com.xinchen.spring.AppTest;
import com.xinchen.spring.base.entity.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xinchen
 * @version 1.0
 * @date 17/04/2019 16:56
 */
public class ResourceRepositoryTest extends AppTest {

    @Autowired
    private ResourceRepository resourceRepository;

    @Test
    public void insertTest(){
        Resource resource = new Resource();
        resource.setUrl("/app/main");
        resourceRepository.save(resource);
    }
}