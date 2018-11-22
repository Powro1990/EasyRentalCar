package com.easyrentalcar.interfaces;

public class CarAlreadyRentException extends RuntimeException{
    public CarAlreadyRentException(String message) {
        super(message);
    }

    public CarAlreadyRentException(String message, Throwable cause) {
        super(message, cause);
    }
}
