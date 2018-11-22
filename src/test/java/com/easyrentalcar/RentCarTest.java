package com.easyrentalcar;

import com.easyrentalcar.interfaces.CarAlreadyRentException;
import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
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
}

