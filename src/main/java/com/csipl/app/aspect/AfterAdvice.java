package com.csipl.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterAdvice {
	Logger log = LoggerFactory.getLogger(this.getClass());


	
	@Pointcut("execution(* com.csipl.app.controller.EmployeeController.getEmployeeById(..))")
	public void getIdPointCut() {

	}

	@After("getIdPointCut()")
	public void checkId(JoinPoint joinPoint) throws Throwable {
		Object args[] = joinPoint.getArgs();
		log.debug("******************************** Response done for User Id:" + args[0] + "********************************");
	}

}
