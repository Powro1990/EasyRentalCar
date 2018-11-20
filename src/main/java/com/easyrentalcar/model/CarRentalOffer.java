package com.easyrentalcar.model;

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
