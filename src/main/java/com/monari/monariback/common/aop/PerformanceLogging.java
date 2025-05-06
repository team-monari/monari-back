package com.monari.monariback.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceLogging {

    @Around("@annotation(com.monari.monariback.common.aop.ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long end = System.currentTimeMillis();
            String methodName = joinPoint.getSignature().toShortString();
            log.info(" {} 메서드의 실행시간은 {} ms 입니다", methodName, end - start);
        }
    }
}