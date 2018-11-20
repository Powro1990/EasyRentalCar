package com.easyrentalcar.model;

import java.util.Objects;

public class CreateOfferCommand {

    private String brand;
    private String model;
    private String vin;
    private String location;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CreateOfferCommand(String brand, String model, String vin, String location) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.location = location;
    }

    public CreateOfferCommand() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOfferCommand that = (CreateOfferCommand) o;
        return Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model) &&
                Objects.equals(vin, that.vin) &&
                Objects.equals(location, that.location);
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "CreateOfferCommand{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, vin, location);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }
}
