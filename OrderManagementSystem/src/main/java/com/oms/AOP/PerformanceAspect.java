package com.oms.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class PerformanceAspect {

	@Around("execution( * com.orm.*.*.* (..) )")
	public Object performance(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		Long st = System.currentTimeMillis();
		
		obj = pjp.proceed();
		
		Long et = System.currentTimeMillis();
		
		log.info("Time taken to execute the method. =="+pjp.getTarget().toString()+"=="+pjp.getSignature().getName()+"=="+(et-st));
		
		return obj;
	}
}
