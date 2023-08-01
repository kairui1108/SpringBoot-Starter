package com.ruikai.starter.retry.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/7/25 21:29
 */
public class MethodRetryerFactory {

    public static ConcurrentHashMap<String, MethodRetryer> cache = new ConcurrentHashMap<>();

    public static MethodRetryer getRetryer(int retryTime, int waitTime, Class<? extends Exception> type) {
        String key = "" + retryTime + waitTime + type.toString();
        return cache.computeIfAbsent(key, k -> new MethodRetryer(retryTime, waitTime, type));
    }
}
