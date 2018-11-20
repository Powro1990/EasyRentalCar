package com.easyrentalcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;
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
        CreateOfferCommand expectedCommand = new CreateOfferCommand(brand, model, vin, location);

        //when
        // @formatter:off
        mockMvc.perform(
                post("/offers")
                .param("brand", brand)
                .param("model", model)
                .param("vin", vin)
                .param("location", location)
        )

        .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", String.format("http://localhost:%s/offers", port)));
        // @formatter:on

        //then
        verify(rentalManager, times(1)).postOffer(expectedCommand);
    }
}
