package com.csipl.app.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging execution of service.
 * @author shubham yaduwanshi
 *
 */
@Aspect
@Component
public class LogAroundAdvice {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
	/*
	 * @Around("execution(* com.howtodoinjava.app.service.impl.EmployeeManagerImpl.*(..))"
	 * )
	 */ 
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }
    
    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.csipl.app..*)" +
        " || within(com.csipl.app.service..*)" +
        " || within(com.csipl.app.controller..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Point cut, the implementations are in the advice.
    }
    
    
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Start Execution : {}.{}() ::", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("End Execution: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}