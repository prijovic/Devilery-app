package com.ftn.sbnz.service.translations;

public enum Codes implements Translation {
    PASSWORD_RESET_LINK("password_reset_link"),
    PASSWORD_RESET_EMAIL_SUBJECT("password_reset_email_subject"),
    PASSWORD_RESET_REQUEST_SUCCESS("password_reset_request_success"),

    USER_SIGN_UP_ACTIVATION_EMAIL("user_sign_up_activation_email"),

    USER_SIGN_UP_ACTIVATION_EMAIL_SUBJECT("user_sign_up_activation_email_subject"),

    USER_EMAIL_ACTIVATION_SUCCESS("user_email_activation_success");

    private final String code;

    Codes(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
