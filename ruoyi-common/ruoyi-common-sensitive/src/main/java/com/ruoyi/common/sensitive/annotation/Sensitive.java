package com.ruoyi.common.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.ruoyi.common.sensitive.core.SensitiveStrategy;
import com.ruoyi.common.sensitive.handler.SensitiveHandler;
import tools.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据脱敏注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveHandler.class)
public @interface Sensitive {
    SensitiveStrategy strategy();

    String roleKey() default "";

    String perms() default "";
}
