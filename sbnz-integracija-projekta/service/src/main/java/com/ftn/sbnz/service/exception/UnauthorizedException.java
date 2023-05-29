package com.ftn.sbnz.service.exception;

public class UnauthorizedException extends CustomRuntimeException {
    public UnauthorizedException() {
        super(ExceptionKeys.UNAUTHORIZED);
    }
}
