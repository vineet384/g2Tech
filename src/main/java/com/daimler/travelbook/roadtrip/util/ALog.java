package com.daimler.travelbook.roadtrip.util;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ALog {
    boolean startLog() default false;

    boolean endLog() default false;

    boolean performanceLog() default true;
}
