package com.easyrentalcar;

import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;

public class OfferTestFixture {

    public static CreateOfferCommand defaultOffer() {

        return new CreateOfferCommand("Mercedes", "SL65AMG", "1234", "Bydgoszcz", 1000.0);
    }

    public static CreateOfferCommand offerWithPrice(double price) {
        CreateOfferCommand defaultOffer = defaultOffer();
        defaultOffer.setPrice(price);
        return defaultOffer;
    }

    public static CreateOfferCommand offerWithLocation(String location){
        CreateOfferCommand defaultOffer = defaultOffer();
        defaultOffer.setLocation(location);
        return defaultOffer;
    }
}
