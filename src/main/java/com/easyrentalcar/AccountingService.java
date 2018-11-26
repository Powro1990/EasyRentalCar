package com.easyrentalcar;

import com.easyrentalcar.model.CarRentalOffer;

/**
 * A service to calculate and update earnings in EasyCarRental.
 */
public interface AccountingService {
    /**
     * Returns the sum of total earnings in this application.
     *
     * @return the sum of total earnings
     */
    double totalEarnings();

    /**
     * Updates total earnings when new offer is rent.
     *
     * @param offer a rent offer
     */
    void updateEarnings(CarRentalOffer offer);
}
