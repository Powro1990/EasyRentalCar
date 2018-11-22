package com.easyrentalcar.services;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

// @Service
public class InMemoryCarRentalManager implements CarRentalManager {

    private Collection<CarRentalOffer> offers = new ArrayList<>();

    @Override
    public CarRentalOffer postOffer(CreateOfferCommand command) {
        CarRentalOffer rentalOffer = CarRentalOffer.fromCommand(command);
        return rentalOffer;
    }

    @Override
    public Collection<CarRentalOffer> findAllOffers() {
        return offers;
    }

    @Override
    public void rentCar(Long id, String lessee) {

    }

    @Override
    public Optional<CarRentalOffer> findOffer(Long id) {
        return Optional.empty();
    }

}
