package com.vorh.spring.bpp.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by vorh on 7/1/18.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableAPI {

    Type type() default Type.NONE;

    enum Type {
        NONE,
        SERVICE,
        USER
    }
}
