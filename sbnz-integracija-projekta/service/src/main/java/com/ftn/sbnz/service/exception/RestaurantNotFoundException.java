package com.ftn.sbnz.service.exception;

public class RestaurantNotFoundException extends CustomRuntimeException {
    public RestaurantNotFoundException() {
        super(ExceptionKeys.RESTAURANT_NOT_FOUND);
    }
}
