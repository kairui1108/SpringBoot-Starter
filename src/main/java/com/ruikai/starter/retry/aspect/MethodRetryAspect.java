package com.ruikai.starter.retry.aspect;

import com.ruikai.starter.retry.annotation.RetryMethod;
import com.ruikai.starter.retry.core.MethodRetryer;
import com.ruikai.starter.retry.core.MethodRetryerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/7/25 21:15
 */
@Aspect
public class MethodRetryAspect {

    private final Logger logger = LoggerFactory.getLogger(MethodRetryAspect.class);

    @Around("@annotation(retryMethod)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint, RetryMethod retryMethod) throws Throwable {
        int retryTimes = retryMethod.retryTimes();
        int waitTime = retryMethod.waitTime();
        Class<? extends Exception> exception = retryMethod.exception();
        MethodRetryer retryer = MethodRetryerFactory.getRetryer(retryTimes, waitTime, exception);
        return retryer.doAction(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                throw exception.getDeclaredConstructor(e.getClass()).newInstance(e);
            }
        });
    }
}
