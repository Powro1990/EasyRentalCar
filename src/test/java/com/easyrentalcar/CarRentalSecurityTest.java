package com.easyrentalcar;

import com.easyrentalcar.model.User;
import com.easyrentalcar.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private UserService userService;

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
        // given
        User user = createUserWithEmailAndPassword("goobar@test.com", "test12345678");
        userService.registerUser(user);

        // when
        mockMvc.perform(post("/login").with(csrf())
                .param("username", user.getEmail())
                .param("password", user.getPassword()))

                // then
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/offerstorent"));
    }

    private User createUserWithEmailAndPassword(String email, String password) {
        User user = new User();
        user.setName("name");
        user.setLastname("lastname");
        user.setPhone("123123");
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
