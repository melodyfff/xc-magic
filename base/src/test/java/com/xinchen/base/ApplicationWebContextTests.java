package com.xinchen.base;

import com.xinchen.base.config.WebConfig;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author xinchen
 * @version 1.0
 * @date 05/03/2019 11:10
 */
@ContextHierarchy({
        @ContextConfiguration(classes = {WebConfig.class})
})
public class ApplicationWebContextTests extends ApplicationContextTests{

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}
