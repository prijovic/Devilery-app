package com.ftn.sbnz.service.exception;

public class UserNotFoundException extends CustomRuntimeException {
    public UserNotFoundException() {
        super(ExceptionKeys.USER_NOT_FOUND);
    }
}
