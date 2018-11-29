package com.easyrentalcar;

import com.easyrentalcar.model.User;
import com.easyrentalcar.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.validation.constraints.NotNull;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @DisplayName("should register user and find by email")
    @Test
    void test() throws Exception {
        // given
        String email = "mike@buildmysoftware.pro";
        String pass = "password";
        User user = new User("mike", "wojtyna", email, "123", pass);
        when(passwordEncoder.encode(pass)).thenReturn(pass+123456);

        // when
        userService.registerUser(user);

        // then
        UserDetails userDetails = userService.loadUserByUsername(email);
        assertThat(userDetails.getUsername()).isEqualTo(email);
        assertThat(userDetails.getPassword()).isEqualTo(passwordEncoder.encode(pass));
    }

    @DisplayName("should throw exception when user with given username doesn't exist")
    @Test
    void test1() throws Exception {
        // when
        Throwable e = catchThrowable(() -> userService.loadUserByUsername("goobar"));

        // then
        assertThat(e).isInstanceOf(UsernameNotFoundException.class);
    }
}
