package com.xinchen.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author xinchen
 * @version 1.0
 * @date 04/03/2019 14:33
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationMockTests {

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
}
