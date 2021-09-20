package com.h3yon.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 파라미터로 선언된 객체에서만 사용 가능하다는 의미
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    /**
     * 어노테이션
     */
}
