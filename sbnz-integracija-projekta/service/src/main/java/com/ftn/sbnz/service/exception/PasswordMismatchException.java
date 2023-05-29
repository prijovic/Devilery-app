package com.ftn.sbnz.service.exception;

public class PasswordMismatchException extends CustomRuntimeException {
    public PasswordMismatchException() {
        super(ExceptionKeys.PASSWORD_MISMATCH);
    }
}
