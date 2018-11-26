package com.easyrentalcar;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import com.easyrentalcar.services.AccountingService;
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
    @Autowired
    private AccountingService accService;


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
        CreateOfferCommand offerCommand = new CreateOfferCommand("BMW", "X6", "abc", "Bydgoszcz", 1000.0);
        return offerCommand;
    }

    @DisplayName("should find all posted offers")
    @Test
    void test1() throws Exception {
        // given
        CreateOfferCommand offerCommand1 = new CreateOfferCommand("BMW", "X6", "abc", "Bydgoszcz", 1000.0);
        CreateOfferCommand offerCommand2 = new CreateOfferCommand("Fiat", "500", "abcd", "Bydgoszcz", 1000.0);
        rentalManager.postOffer(offerCommand1);
        rentalManager.postOffer(offerCommand2);

        // when
        Collection<CarRentalOffer> allOffers = rentalManager.findAllOffers();

        // then
        assertThat(allOffers).hasSize(2);
    }

    @DisplayName("should earn 10.00 from rental car offer when offer price is 100.00")
    @Test
    void test2() throws Exception {
        // given
        CarRentalOffer offer = rentalManager.postOffer(OfferTestFixture.offerWithPrice(100.00));

        // when
        rentalManager.rentCar(offer.getId(), anyLessee());

        // then
        assertThat(accService.totalEarnings()).isEqualTo(10.00);
    }

    @DisplayName("should earn 30 when first commission is 10, second commission is 20")
    @Test
    void test3() throws Exception {

        // given
        CarRentalOffer offer = rentalManager.postOffer(OfferTestFixture.offerWithPrice(100.00));
        CarRentalOffer offer2 = rentalManager.postOffer(OfferTestFixture.offerWithPrice(200.00));
        // when
        rentalManager.rentCar(offer.getId(), anyLessee());
        rentalManager.rentCar(offer2.getId(), anyLessee());

        // then

        assertThat(accService.totalEarnings()).isEqualTo(30.00);
    }

    private String anyLessee() {
        return "goobar";
    }
}

