package com.easyrentalcar;

import java.util.Collection;

public interface CarRentalManager {
    CarRentalOffer postOffer(CreateOfferCommand command);

    Collection<CarRentalOffer> findAllOffers();
}
