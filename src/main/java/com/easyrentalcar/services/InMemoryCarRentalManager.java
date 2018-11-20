package com.easyrentalcar.services;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
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
