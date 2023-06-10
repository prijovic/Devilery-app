package com.ftn.sbnz.service.exception;

public class OrderNotFound extends CustomRuntimeException {
    public OrderNotFound() {
        super(ExceptionKeys.ORDER_NOT_FOUND);
    }
}
