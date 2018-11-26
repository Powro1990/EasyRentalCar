package com.easyrentalcar.services;

import com.easyrentalcar.model.CarRentalOffer;
import org.springframework.stereotype.Service;

/**
 * A service to calculate and update earnings in EasyCarRental.
 */
@Service
public class AccountingService {

    private static final double commission = 0.1;
    private double totalEarings;

    /**
     * Returns the sum of total earnings in this application.
     *
     * @return the sum of total earnings
     */
    public double totalEarnings() {

        return totalEarings;

    }

    /**
     * Updates total earnings when new offer is rent.
     *
     * @param offer a rent offer
     */
    public void updateEarnings(CarRentalOffer offer) {

        totalEarings += offer.getPrice() * commission;

    }
}
