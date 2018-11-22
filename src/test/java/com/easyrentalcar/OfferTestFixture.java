package com.easyrentalcar;

import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;

public class OfferTestFixture {

    public static CreateOfferCommand defaultOffer(){

        return new CreateOfferCommand("Mercedes", "SL65AMG", "1234", "Bydgoszcz");
    }
}
