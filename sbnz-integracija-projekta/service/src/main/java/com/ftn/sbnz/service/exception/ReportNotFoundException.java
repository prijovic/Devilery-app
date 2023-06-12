package com.ftn.sbnz.service.exception;

public class ReportNotFoundException extends CustomRuntimeException {
    public ReportNotFoundException() {
        super(ExceptionKeys.REPORT_NOT_FOUND);
    }
}
