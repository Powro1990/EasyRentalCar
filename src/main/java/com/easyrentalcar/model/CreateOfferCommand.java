package com.easyrentalcar.model;

import java.util.Objects;

public class CreateOfferCommand {

    private String brand;
    private String model;
    private String vin;
    private String location;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

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

    public CreateOfferCommand(String brand, String model, String vin, String location, Double price) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.location = location;
        this.price = price;
    }

    public CreateOfferCommand() {
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOfferCommand that = (CreateOfferCommand) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model) &&
                Objects.equals(vin, that.vin) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, vin, location, price);
    }

    @Override
    public String toString() {
        return "CreateOfferCommand{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                '}';
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
