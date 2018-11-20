package com.easyrentalcar;

public class InMemoryCarRentalManager implements CarRentalManager {

    @Override
    public CarRentalOffer postOffer(CreateOfferCommand command) {
        CarRentalOffer rentalOffer = new CarRentalOffer( command.getBrand(),command.getModel(), command.getVin(), command.getLocation());
        return rentalOffer;
    }
}
