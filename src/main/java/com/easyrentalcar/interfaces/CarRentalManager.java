package com.easyrentalcar.interfaces;

import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CarRentalManager {
    CarRentalOffer postOffer(CreateOfferCommand command);

    Collection<CarRentalOffer> findAllOffers();

    void rentCar(Long id, String lessee);

    Optional<CarRentalOffer> findOffer(Long id);

    List<CarRentalOffer> findOfferByLocation(String bydgoszcz);
}
