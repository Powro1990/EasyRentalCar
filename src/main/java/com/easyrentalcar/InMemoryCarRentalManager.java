package com.easyrentalcar;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryCarRentalManager implements CarRentalManager {

    private Collection<CarRentalOffer> offers = new ArrayList<>();

    @Override
    public CarRentalOffer postOffer(CreateOfferCommand command) {
        CarRentalOffer rentalOffer = new CarRentalOffer( command.getBrand(),command.getModel(), command.getVin(), command.getLocation());
        offers.add(rentalOffer);
        return rentalOffer;
    }

    @Override
    public Collection<CarRentalOffer> findAllOffers() {
        return offers;
    }

}
