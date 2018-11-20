package com.easyrentalcar.interfaces;

import com.easyrentalcar.model.CarRentalOffer;
import com.easyrentalcar.model.CreateOfferCommand;

import java.util.Collection;

public interface CarRentalManager {
    CarRentalOffer postOffer(CreateOfferCommand command);

    Collection<CarRentalOffer> findAllOffers();
}
