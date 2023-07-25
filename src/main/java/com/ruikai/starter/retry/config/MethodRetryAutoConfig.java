package com.ruikai.starter.retry.config;

import com.ruikai.starter.retry.aspect.MethodRetryAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/7/25 22:04
 */
public class MethodRetryAutoConfig {

    private final Logger logger = LoggerFactory.getLogger(MethodRetryAutoConfig.class);

    @Bean
    public MethodRetryAspect methodRetryAspect() {
        return new MethodRetryAspect();
    }
}
