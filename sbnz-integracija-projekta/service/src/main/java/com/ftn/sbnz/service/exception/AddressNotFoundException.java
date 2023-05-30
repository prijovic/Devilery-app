package com.ftn.sbnz.service.exception;

public class AddressNotFoundException extends CustomRuntimeException {
    public AddressNotFoundException() {
        super(ExceptionKeys.ADDRESS_NOT_FOUND);
    }
}
