package com.ruoyi.common.ratelimiter.annotation;

import com.ruoyi.common.ratelimiter.enums.LimitType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 配置限流键，支持用 Spring EL 表达式读取方法参数。
     *
     * @return 自定义键或表达式，默认为空
     */
    String key() default "";

    /**
     * 配置限流时间窗口。
     *
     * @return 时间窗口秒数
     */
    int time() default 60;

    /**
     * 配置时间窗口内允许的请求次数。
     *
     * @return 允许的请求次数
     */
    int count() default 100;

    /**
     * 配置全局、IP 或实例限流类型。
     *
     * @return 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;

    /**
     * 配置超限时的提示消息。
     *
     * @return 提示消息
     */
    String message() default "访问过于频繁，请稍候再试";
}
