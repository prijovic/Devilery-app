package com.ftn.sbnz.service.exception;

public class UserNotActiveException extends CustomRuntimeException {
    public UserNotActiveException() {
        super(ExceptionKeys.USER_NOT_ACTIVE);
    }
}
