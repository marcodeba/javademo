package com.demo.javademo.io.excel.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
    //设置名称
    public String name() default "";
}