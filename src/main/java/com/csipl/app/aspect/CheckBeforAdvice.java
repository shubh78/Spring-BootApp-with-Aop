package com.csipl.app.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckBeforAdvice {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.csipl.app..*)" + " || within(com.csipl.app.service..*)"
			+ " || within(com.csipl.app.controller..*)")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Point cut, the implementations are in the advice.
	}

	@Pointcut("execution(* com.csipl.app.controller.EmployeeController.getEmployeeById(..))")
	public void getIdPointCut() {

	}

	@Before("getIdPointCut()")
	public void checkId(JoinPoint joinPoint) throws Throwable {
		Object args[] = joinPoint.getArgs();
		log.debug("********************************User Id:"+args[0]+"********************************");
		if ((Long) args[0] <= 1)
			log.error("Invalid Id: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		else
			log.debug("Check Id Format: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	}

}
