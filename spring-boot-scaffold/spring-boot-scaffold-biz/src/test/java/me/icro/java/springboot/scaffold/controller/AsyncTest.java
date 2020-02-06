package me.icro.java.springboot.scaffold.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-02-06 3:53 下午
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AsyncTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_data() throws Exception {
        mockMvc.perform(get("/async/movies"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andDo(print());
    }
}
