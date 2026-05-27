package com.example.session12_it211_bai1.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.session12_it211_bai1.controller.*.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        log.info("controller - Method: {} | Tham số: {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.example.session12_it211_bai1.service.BookService.*(..))", returning = "result")
    public void logAfterService(JoinPoint joinPoint, Object result) {
        log.info("service - Method: {} | Kết quả trả về: {}",
                joinPoint.getSignature().getName(),
                result);
    }

    @Around("execution(* com.example.session12_it211_bai1.controller.*.*(..))")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed(); // Chạy method chính
        long executionTime = System.currentTimeMillis() - start;

        log.info("Controller - Method: {} thực thi trong {} ms",
                joinPoint.getSignature().getName(),
                executionTime);
        return proceed;
    }
}