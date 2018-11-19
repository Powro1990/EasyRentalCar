package com.easyrentalcar;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Car car;
    private CarRentalManager rentalManager;

    @BeforeEach
    void initialize() throws Exception {
        car = new Car("BMW", "X6", "abc", "Bydgoszcz");
    }

    @DisplayName("should create a new car rental offer")
    @Test
    void test0() throws Exception {
        // given
        CreateOfferCommand command = createCommand();

        // when
        CarRentalOffer offer = rentalManager.postOffer(command);

        // then
        assertThat(car.getBrand()).isEqualTo("BMW");
        assertThat(car.getModel()).isEqualTo("X6");
        assertThat(car.getVin()).isEqualTo("abc");
        assertThat(car.getLocation()).isEqualTo("Bydgoszcz");
    }

    private CreateOfferCommand createCommand() {
        return null;
    }

    @DisplayName("user should have a possibility add a car")
    @Test
    void test1() throws Exception {
        // given
        User user = new User();
        // when
        user.addCar(car);
        // then
        assertThat(user.getCars()).isEqualTo(car);
    }
}
