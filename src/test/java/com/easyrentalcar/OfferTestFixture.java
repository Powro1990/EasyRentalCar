package com.easyrentalcar;

import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;

public class OfferTestFixture {

    public static CreateOfferCommand defaultOfferCommand() {

        return new CreateOfferCommand("Mercedes", "SL65AMG", "1234", "Bydgoszcz", 1000.0);
    }

    public static CreateOfferCommand offerCommandWithPrice(double price) {
        CreateOfferCommand defaultOffer = defaultOfferCommand();
        defaultOffer.setPrice(price);
        return defaultOffer;
    }

    public static CreateOfferCommand offerCommandWithLocation(String location) {
        CreateOfferCommand defaultOffer = defaultOfferCommand();
        defaultOffer.setLocation(location);
        return defaultOffer;
    }

    public static CarRentalOffer offerWithLocation(String location) {
        return new CarRentalOffer(defaultBrand(), defaultModel(), defaultVin(), location, defaultPrice());
    }

    private static double defaultPrice() {
        return 1000.00;
    }

    private static String defaultVin() {
        return "123456";
    }

    private static String defaultModel() {
        return "SL65AMG";
    }

    private static String defaultBrand() {
        return "Mercedes";
    }
}
