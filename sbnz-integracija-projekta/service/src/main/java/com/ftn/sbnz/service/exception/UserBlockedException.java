package com.ftn.sbnz.service.exception;

public class UserBlockedException extends CustomRuntimeException {
    public UserBlockedException() {
        super(ExceptionKeys.USER_BLOCKED);
    }
}