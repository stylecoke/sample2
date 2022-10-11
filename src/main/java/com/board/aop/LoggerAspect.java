package com.board.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.board.controller.*Controller.*(..)) or execution(* com.board.service.*Impl.*(..)) or execution(* com.board.mapper.*Mapper.*(..))")
	public void printLog(JoinPoint joinPoint) throws Throwable {

		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();

		if (name.contains("Controller") == true) {
			type = "Sample2 Controller ===> ";

		} else if (name.contains("Service") == true) {
			type = "Sample2 ServiceImpl ===> ";

		} else if (name.contains("Mapper") == true) {
			type = "Sample2 Mapper ===> ";
		}

		logger.info(type + name + "." + joinPoint.getSignature().getName() + "()");
	}

}
