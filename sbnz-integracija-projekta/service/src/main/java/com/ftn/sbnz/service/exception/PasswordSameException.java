package com.ftn.sbnz.service.exception;

public class PasswordSameException extends CustomRuntimeException {
    public PasswordSameException() {
        super(ExceptionKeys.PASSWORD_SAME);
    }
}
