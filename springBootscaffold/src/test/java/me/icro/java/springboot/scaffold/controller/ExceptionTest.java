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
 * @since 2020-01-15 11:42 AM
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ExceptionTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_400_if_param_not_valid() throws Exception {
        mockMvc.perform(get("/api/illegalArgumentException"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("参数错误!"));
    }

    @Test
    public void should_return_404_if_resource_not_found() throws Exception {
        mockMvc.perform(get("/api/resourceNotFoundException"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message").value("the resource Not Found!"));
    }

    @Test
    public void should_return_404_if_resource_not_found2() throws Exception {
        mockMvc.perform(get("/api/resourceNotFoundException2"))
                .andExpect(status().is(404))
                .andDo(print());
                //.andExpect(jsonPath("$.message").value("the resource Not Found!"));
    }

    @Test
    public void should_return_404_if_resource_not_found3() throws Exception {
        mockMvc.perform(get("/api/resourceNotFoundException3"))
                .andExpect(status().is(404))
                .andDo(print());
                //.andExpect(jsonPath("$.message").value("the resource Not Found!"));
    }
}
