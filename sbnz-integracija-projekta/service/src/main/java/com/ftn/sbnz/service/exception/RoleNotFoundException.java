package com.ftn.sbnz.service.exception;

public class RoleNotFoundException extends CustomRuntimeException {
    public RoleNotFoundException() {
        super(ExceptionKeys.ROLE_NOT_FOUND);
    }
}
