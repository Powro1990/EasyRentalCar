package com.easyrentalcar.services;

import com.easyrentalcar.interfaces.CarAlreadyRentException;
import com.easyrentalcar.interfaces.CarRentalManager;
import com.easyrentalcar.interfaces.OfferDoesntExistException;
import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;
import com.easyrentalcar.repositories.CarRentalOfferRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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

    @Override
    public void rentCar(Long id, String lessee) {
        carRentalOfferRepository.findById(id).map(offer -> {
            if (offer.lessee().isPresent()) {
                throw new CarAlreadyRentException("car is already leased");
            }
            offer.setLessee(lessee);
            carRentalOfferRepository.save(offer);
            return offer;
        }).orElseThrow(() -> new OfferDoesntExistException(String.format("Offer with id %s doesn't exist", id)));
    }

    @Override
    public Optional<CarRentalOffer> findOffer(Long id) {
        return carRentalOfferRepository.findById(id);
    }
}
