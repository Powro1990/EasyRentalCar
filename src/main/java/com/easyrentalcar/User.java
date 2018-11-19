package com.easyrentalcar;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String lastname;
    private List cars;

    public User() {
    }

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List getCars() {
        return cars;
    }

    public void setCars(List cars) {
        this.cars = cars;
    }

    public List <Car> addCar(Car car) {
        List <Car> cars = new ArrayList();
        cars.add(car);
        return cars;

    }
}
