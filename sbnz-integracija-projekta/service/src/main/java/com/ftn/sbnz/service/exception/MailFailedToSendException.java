package com.ftn.sbnz.service.exception;

public class MailFailedToSendException extends CustomRuntimeException {
    public MailFailedToSendException() {
        super(ExceptionKeys.MAIL_FAILED);
    }
}
