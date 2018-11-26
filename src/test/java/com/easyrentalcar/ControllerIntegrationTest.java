package com.easyrentalcar;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig
@WebMvcTest
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarRentalManager rentalManager;

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

    @DisplayName("should display all offers to rental on get /offerstorent")
    @Test
    void test2() throws Exception {
    	// given
        Collection<CarRentalOffer> cars = new ArrayList<>();
        cars.add(new CarRentalOffer("bmw", "x2", "acd", "Bydgoszcz", 900));
        cars.add(new CarRentalOffer("bmw", "x3", "adcd", "Bydgoszcz", 300));
        cars.add(new CarRentalOffer("bmw", "x4", "acbd", "Bydgoszcz", 450));
        when(rentalManager.findAllOffers()).thenReturn(cars);

        // when
        // @formatter:off
        mockMvc.perform(get("/offerstorent"))

                // then
                .andExpect(status().isOk())
                .andExpect(view().name("offerstorent"))
                .andExpect(model().attribute("offerstorent", cars));
        // @formatter:on
    	// then
    }


}
