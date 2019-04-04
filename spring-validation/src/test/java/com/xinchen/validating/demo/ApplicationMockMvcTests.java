package com.xinchen.validating.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationMockMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkPersonInfoWhenNameMissingNameThenFailure() throws Exception {
        mockMvc
                .perform(post("/").param("age", "20"))
                .andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenNameTooShortThenFailure() throws Exception{
        mockMvc.perform(post("/").param("name", "R").param("age", "20"))
                .andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenAgeTooYoungThenFailure() throws Exception{
        mockMvc.perform(post("/").param("age", "1").param("name", "Sam"))
                .andExpect(model().hasErrors());
    }

    @Test
    public void checkPersonInfoWhenValidRequestThenSuccess() throws Exception {
        mockMvc.perform(post("/").param("name", "Rob").param("age", "20"))
                .andExpect(model().hasNoErrors());
    }

}
