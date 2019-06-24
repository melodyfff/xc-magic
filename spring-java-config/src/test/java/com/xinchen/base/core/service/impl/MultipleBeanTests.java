package com.xinchen.base.core.service.impl;

import com.xinchen.base.ApplicationWebContextTests;
import com.xinchen.base.core.service.MultipleBeanService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xinchen
 * @version 1.0
 * @date 12/06/2019 17:49
 */
public class MultipleBeanTests extends ApplicationWebContextTests {
    @Autowired
    private MultipleBeanService multipleBeanService;


    @Test
    public void test(){
        for (int i = 0; i < 10; i++) {
            multipleBeanService.test();
        }
    }
}
