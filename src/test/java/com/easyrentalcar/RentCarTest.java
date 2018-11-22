package com.easyrentalcar;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import org.assertj.core.api.Assertions;
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
        String brand = "BMW";
        String model = "850i";
        String vin = "abc123";
        String location = "Bydgoszcz";
        String brand2 = "AUDI";
        String model2 = "Q8";
        String vin2 = "AAAAA321";
        String location2 = "Bydgoszcz";
        CarRentalOffer offer1 = manager.postOffer(new CreateOfferCommand(brand, model, vin, location));
        CarRentalOffer offer2 = manager.postOffer(new CreateOfferCommand(brand2, model2, vin2, location2));
        String lessee = "goobar";

        // when
        manager.rentCar(offer2.getId(), lessee);

        // then
        CarRentalOffer usedOffer2 = manager.findOffer(offer2.getId()).get();
        assertThat(usedOffer2.lessee()).hasValue(lessee);
    }

    // @DisplayName("car from offer cannot be used again when user already rent a car")
}
