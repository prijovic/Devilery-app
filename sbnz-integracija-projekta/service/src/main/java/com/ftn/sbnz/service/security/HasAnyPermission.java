package com.ftn.sbnz.service.security;

import com.ftn.sbnz.model.models.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasAnyPermission {
    Permission[] value();
}
