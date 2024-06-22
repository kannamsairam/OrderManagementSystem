package com.oms.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
	
	@Before("execution( * com.orm.*.*.* (..) )")
	public void beforeStart(JoinPoint jp) {
		log.info("Method call has been started. "+"classname=="+jp.getTarget().toString()+".  methodname=="+jp.getSignature().getName());
	}

	@After("execution( * com.orm.*.*.* (..) )")
	public void afterStart(JoinPoint jp) {
		log.info("Method call has been ended. "+"classname=="+jp.getTarget().toString()+".  methodname=="+jp.getSignature().getName());
	}
}
