package com.ftn.sbnz.service.exception;

public class UserAlreadyExistsException extends CustomRuntimeException {
    public UserAlreadyExistsException() {
        super(ExceptionKeys.USER_ALREADY_EXISTS);
    }
}