package com.easyrentalcar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Entity
public class CarRentalOffer {
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String vin;
    @NotNull
    private String location;

    private String lessee;

    public static CarRentalOffer fromCommand(CreateOfferCommand command) {
        return new CarRentalOffer(command.getBrand(), command.getModel(), command.getVin(), command.getLocation());
    }

    public CarRentalOffer(String brand, String model, String vin, String location) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.location = location;
    }

    public CarRentalOffer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarRentalOffer that = (CarRentalOffer) o;
        return Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model) &&
                Objects.equals(vin, that.vin) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, vin, location);
    }

    @Override
    public String toString() {
        return "CarRentalOffer{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public Optional<String> lessee() {
        return Optional.ofNullable(lessee);
    }
}
