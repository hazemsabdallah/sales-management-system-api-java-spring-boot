package com.example.salesmanagment.logging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Pointcut("execution(* com.example.salesmanagment.service.*.*(..))")
    private void ServicePackagePointcut() {}

    @Before("ServicePackagePointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().toShortString();
        log.info("======>> Before Method Execution - {}", methodName);

        Object[] args = joinPoint.getArgs();

        for (Object arg: args) {
            log.info("======>> Passed Arguments - {} - {}", methodName, arg);
        }
    }

    @AfterReturning(pointcut = "ServicePackagePointcut()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {

        String methodName = joinPoint.getSignature().toShortString();
        log.info("======>> After Method Executed Successfully - {}", methodName);

        log.info("======>> Result - {} - {}", methodName, result);
    }

    @AfterThrowing(pointcut = "ServicePackagePointcut()", throwing = "exception")
    public void AfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {

        String methodName = joinPoint.getSignature().toShortString();
        log.info("======>> After Method Failure - {}", methodName);

        log.info("======>> Error - {} - {}", methodName, exception.getMessage());
    }
}
