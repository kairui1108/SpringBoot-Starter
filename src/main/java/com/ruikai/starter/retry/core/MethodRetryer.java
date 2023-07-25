package com.ruikai.starter.retry.core;

import io.github.itning.retry.Retryer;
import io.github.itning.retry.RetryerBuilder;
import io.github.itning.retry.strategy.stop.StopStrategies;
import io.github.itning.retry.strategy.wait.WaitStrategies;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/7/25 21:25
 */
public class MethodRetryer {

    private final Retryer<Object> retryer;

    public MethodRetryer(int retryTimes, int waitTime, Class<? extends Exception> exceptionType) {
        retryer = RetryerBuilder.newBuilder()
                .retryIfExceptionOfType(exceptionType)
                .withWaitStrategy(WaitStrategies.fixedWait(waitTime, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(retryTimes))
                .build();
    }

    public Object doAction(Callable<Object> callable) throws Throwable {
        return retryer.call(callable);
    }

    public void doAction(Runnable runnable) throws Throwable {
        retryer.call(runnable);
    }
}
