package com.easyrentalcar.interfaces;

public class OfferDoesntExistException extends RuntimeException {
    public OfferDoesntExistException(String message) {
        super(message);
    }

    public OfferDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
