package com.easyrentalcar;


import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
@org.junit.jupiter.api.DisplayName("user should have a possibility add a car")
@org.junit.jupiter.api.Test
void test() throws Exception {
	// given
User user = new User();
Car car = new Car();
	// when
user.addCar(car);
	// then
	Assertions.assertTha

	org.junit.jupiter.api.Assertions.fail("Write your test");
}
}
