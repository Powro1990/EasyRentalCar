package com.easyrentalcar.model;

import java.util.Objects;

public class CarRentalOffer {

    private String brand;
    private String model;
    private String vin;
    private String location;

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

}
