package com.javatest.java_test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.javatest.java_test.configuration.JwtSecurityConfig;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(JwtSecurityConfig.class)
public @interface EnableJwtSecurity{

}