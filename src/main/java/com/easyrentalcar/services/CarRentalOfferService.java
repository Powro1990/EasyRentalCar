package com.easyrentalcar.services;

import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import com.easyrentalcar.repositories.CarRentalOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarRentalOfferService implements CarRentalManager {

    private CarRentalOfferRepository carRentalOfferRepository;

    public CarRentalOfferService(CarRentalOfferRepository repo) {
        carRentalOfferRepository = repo;
    }

    @Override
    public CarRentalOffer postOffer(CreateOfferCommand command) {
        CarRentalOffer carRentalOffer = CarRentalOffer.fromCommand(command);

        return carRentalOfferRepository.save(carRentalOffer);
    }

    @Override
    public Collection<CarRentalOffer> findAllOffers() {
        return carRentalOfferRepository.findAll();
    }
}
