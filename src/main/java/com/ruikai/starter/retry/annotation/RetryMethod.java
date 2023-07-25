package com.ruikai.starter.retry.annotation;

import java.lang.annotation.*;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/7/25 21:12
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RetryMethod {

    /**
     * 重试次数
     */
    int retryTimes() default 3;

    /**
     * 等待时长 second
     */
    int waitTime() default 5;

    /**
     * 异常种类
     */
    Class<? extends Exception> exception() default RuntimeException.class;

}
