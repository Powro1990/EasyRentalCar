package com.easyrentalcar;

import com.easyrentalcar.interfaces.CarAlreadyRentException;
import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.interfaces.OfferDoesntExistException;
import com.easyrentalcar.model.CarRentalOffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RentCarTest {

    @Autowired
    private CarRentalManager manager;

    @DisplayName("lessee can lease a car from offer")
    @Test
    void test() throws Exception {
        // given
        CarRentalOffer offer1 = manager.postOffer(OfferTestFixture.defaultOffer());
        CarRentalOffer offer2 = manager.postOffer(OfferTestFixture.defaultOffer());
        String lessee = "goobar";

        // when
        manager.rentCar(offer2.getId(), lessee);

        // then
        CarRentalOffer usedOffer2 = manager.findOffer(offer2.getId()).get();
        assertThat(usedOffer2.lessee()).hasValue(lessee);
    }

    @DisplayName("car from offer cannot be leased again when user already rent a car from this offer")
    @Test
    void test1() throws Exception {
        //given
        String firstLessee = "goobar";
        String secondLessee = "foobar";
        CarRentalOffer offer = manager.postOffer(OfferTestFixture.defaultOffer());
        manager.rentCar(offer.getId(), firstLessee);

        //when
        Throwable ex = catchThrowable(() -> manager.rentCar(offer.getId(), secondLessee));

        //then
        assertThat(ex).isInstanceOf(CarAlreadyRentException.class);
    }

    @DisplayName("should throw exception when rent a car from non-existing offer")
    @Test
    void test2() {
        // when
        Throwable ex = catchThrowable(() -> manager.rentCar(666L, "any lesser"));

        // then
        assertThat(ex).isInstanceOf(OfferDoesntExistException.class);
    }

    @DisplayName("should display not available when car is already leased")
    @Test
    void test3() throws Exception {
        // given
        String firstLessee = "goobar";
        CarRentalOffer newOffer = manager.postOffer(OfferTestFixture.defaultOffer());
        manager.rentCar(newOffer.getId(), firstLessee);
        CarRentalOffer leasedOffer = manager.findOffer(newOffer.getId()).get();

        // when
        boolean available = leasedOffer.isAvailable();

        // then
        assertThat(available).isFalse();
    }
}

