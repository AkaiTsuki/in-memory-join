package com.example.memoryjoin.core;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JoinInMemory {
    String keyFromSourceData();

    String loader();

    String keyFromJoinData();

    String converter();
}
