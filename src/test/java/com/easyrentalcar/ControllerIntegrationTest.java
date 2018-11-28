package com.easyrentalcar;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import com.easyrentalcar.services.AccountingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig
@WebMvcTest(secure = false)
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarRentalManager rentalManager;
    @MockBean
    private AccountingService accountingService;

    @DisplayName("Should post new offer, then redirect to /offers")
    @Test
    void test() throws Exception {
        //given
        String brand = "BMW";
        String model = "850i";
        String vin = "abc123";
        String location = "Bydgoszcz";
        Double price = 1000.0;
        CreateOfferCommand expectedCommand = new CreateOfferCommand(brand, model, vin, location, price);

        //when
        // @formatter:off
        mockMvc.perform(
                post("/offers")
                        .param("brand", brand)
                        .param("model", model)
                        .param("vin", vin)
                        .param("location", location)
                        .param("price", price.toString())
        )

                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/offers"));
        // @formatter:on

        //then
        verify(rentalManager, times(1)).postOffer(expectedCommand);
    }

    @DisplayName("should display all offers list on get mapping")
    @Test
    void test1() throws Exception {
        //given
        Collection<CarRentalOffer> cars = new ArrayList<>();
        cars.add(new CarRentalOffer("bmw", "x2", "acd", "Bydgoszcz", 100));
        cars.add(new CarRentalOffer("bmw", "x3", "adcd", "Bydgoszcz", 200));
        cars.add(new CarRentalOffer("bmw", "x4", "acbd", "Bydgoszcz", 800000));
        when(rentalManager.findAllOffers()).thenReturn(cars);

        //when
        // @formatter:off
        mockMvc.perform(get("/offers"))

                // then
                .andExpect(status().isOk())
                .andExpect(view().name("caraddform"))
                .andExpect(model().attribute("offers", cars));
        // @formatter:on
    }

    @DisplayName("should display all offers to rental on get /offerstorent and totalEarnings form all offers")
    @Test
    void test2() throws Exception {
        // given
        Collection<CarRentalOffer> cars = new ArrayList<>();
        double totalEarnings = 156.90;
        cars.add(new CarRentalOffer("bmw", "x2", "acd", "Bydgoszcz", 900));
        cars.add(new CarRentalOffer("bmw", "x3", "adcd", "Bydgoszcz", 300));
        cars.add(new CarRentalOffer("bmw", "x4", "acbd", "Bydgoszcz", 450));

        when(rentalManager.findAllOffers()).thenReturn(cars);
        when(accountingService.totalEarnings()).thenReturn(totalEarnings);

        // when
        // @formatter:off
        mockMvc.perform(get("/offerstorent"))

                // then
                .andExpect(status().isOk())
                .andExpect(view().name("offerstorent"))
                .andExpect(model().attribute("offerstorent", cars))
                .andExpect(model().attribute("earnings", totalEarnings));

        // @formatter:on
        // then
    }

    @DisplayName("should update view when user try to find offer by location")
    @Test
    void test3() throws Exception {
        // given
        List<CarRentalOffer> offers = Arrays.asList(OfferTestFixture.offerWithLocation("Bydgoszcz"));
        when(rentalManager.findOfferByLocation("Bydgoszcz")).thenReturn(offers);

        // when
        mockMvc.perform(get("/offerstorent").param("findByLocation","Bydgoszcz"))

        // then
        .andExpect(status().isOk())
                .andExpect(view().name("offerstorent"))
                .andExpect(model().attribute("offerstorent", offers));
    }
}
