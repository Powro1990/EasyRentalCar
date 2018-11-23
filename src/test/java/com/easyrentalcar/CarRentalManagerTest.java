package com.easyrentalcar;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit test for simple App.
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CarRentalManagerTest {

    @Autowired
    private CarRentalManager rentalManager;

    @DisplayName("should create a new car rental offer using properties from command")
    @Test
    void test0() throws Exception {
        // given
        CreateOfferCommand command = createCommand();

        // when
        CarRentalOffer offer = rentalManager.postOffer(command);

        // then
        assertThat(offer.getBrand()).isEqualTo(command.getBrand());
        assertThat(offer.getModel()).isEqualTo(command.getModel());
        assertThat(offer.getVin()).isEqualTo(command.getVin());
        assertThat(offer.getLocation()).isEqualTo(command.getLocation());
    }

    private CreateOfferCommand createCommand() {
        CreateOfferCommand offerCommand = new CreateOfferCommand("BMW", "X6", "abc", "Bydgoszcz");
        return offerCommand;
    }

    @DisplayName("should find all posted offers")
    @Test
    void test1() throws Exception {
        // given
        CreateOfferCommand offerCommand1 = new CreateOfferCommand("BMW", "X6", "abc", "Bydgoszcz");
        CreateOfferCommand offerCommand2 = new CreateOfferCommand("Fiat", "500", "abcd", "Bydgoszcz");
        rentalManager.postOffer(offerCommand1);
        rentalManager.postOffer(offerCommand2);

        // when
        Collection<CarRentalOffer> allOffers = rentalManager.findAllOffers();

        // then
        assertThat(allOffers).hasSize(2);
    }
}
