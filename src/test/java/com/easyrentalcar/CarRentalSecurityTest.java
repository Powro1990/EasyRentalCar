package com.easyrentalcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@SpringJUnitConfig
@AutoConfigureMockMvc
public class CarRentalSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("should redirect to login page when get request for offerstorent page is unauthorized")
    @Test
    void test() throws Exception {
        mockMvc.perform(get("/offerstorent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    @DisplayName("should redirect to login page when get request for offers page is unauthorized")
    @Test
    void test1() throws Exception {
        mockMvc.perform(get("/offers"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost/login"));
    }

    @DisplayName("should user be able to log with correct login and password")
    @Test
    void test2() throws Exception {
        mockMvc.perform(post("/login").with(csrf())
                .param("username", "goobar")
                .param("password", "test1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/offerstorent"));
    }
}
