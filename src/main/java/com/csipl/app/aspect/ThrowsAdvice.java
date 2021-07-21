package com.csipl.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ThrowsAdvice {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointCut() {
	}
	
	  @Pointcut("within(@org.springframework.stereotype.Repository *)" +
	            " || within(@org.springframework.stereotype.Service *)" +
	            " || within(@org.springframework.web.bind.annotation.RestController *)")
	    public void springBeanPointcut() {
	        // Method is empty as this is just a Pointcut, the implementations are in the advices.
	    }
	  
    @Pointcut("within(com.csipl.app..*)" +
            " || within(com.csipl.app.service..*)" +
            " || within(com.csipl.app.controller..*)")
        public void applicationPackagePointcut() {
            // Method is empty as this is just a Pointcut, the implementations are in the advices.
        }
	 @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
	        log.error("Exception: in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
	            joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
	    }
}
