package com.xinchen.base.web;

import com.xinchen.base.ApplicationWebContextTests;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author xinchen
 * @version 1.0
 * @date 05/03/2019 13:04
 */
public class UserControllerTest extends ApplicationWebContextTests {

    @Test
    public void insert() throws Exception {
        this.mockMvc
                .perform(get("/user/insert/1/hello"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void insert2() throws Exception {
        this.mockMvc
                .perform(post("/user/insert").param("id", "1").param("name", "hello"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getTest() throws Exception {
        this.mockMvc.perform(get("/user/get/2"))
                .andExpect(status().isInternalServerError())
                .andDo(print());

    }
}