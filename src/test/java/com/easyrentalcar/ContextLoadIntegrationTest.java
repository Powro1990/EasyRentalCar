package com.easyrentalcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@SpringBootTest
public class ContextLoadIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @DisplayName("should initialize Spring context without exceptions")
    @Test
    void test() throws Exception {
        assertThat(context).isNotNull();
    }
}
