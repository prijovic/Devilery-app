package com.ftn.sbnz.service.exception;

public class MenuItemNotFoundException extends CustomRuntimeException {
    public MenuItemNotFoundException() {
        super(ExceptionKeys.MENU_ITEM_NOT_FOUND);
    }
}
