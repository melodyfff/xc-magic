package com.xinchen.base.web;

import com.xinchen.base.ApplicationWebContextTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author xinchen
 * @version 1.0
 * @date 05/03/2019 11:07
 */
public class MultipleBeanControllerTest extends ApplicationWebContextTests {

    @Autowired
    private HelloController helloController;

    @Test
    public void hello() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
//                .andExpect(view().name(""))
                .andDo(print());

//        Assert.assertNotNull(helloController.hello());
    }
}