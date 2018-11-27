package com.easyrentalcar.repositories;

import com.easyrentalcar.model.CarRentalOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRentalOfferRepository extends JpaRepository<CarRentalOffer, Long> {

    List<CarRentalOffer> findByLocation(String location);
}
