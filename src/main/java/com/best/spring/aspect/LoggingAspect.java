package com.best.spring.aspect;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

  private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Before("SystemArchitecture.controller()")
  public void beforeExecutionOfMethod(JoinPoint joinPoint) {
    logger.info(
        "Enter: {}.{}() with argument[s] = {}",
        joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(),
        Arrays.toString(joinPoint.getArgs()));
  }

  @Before("SystemArchitecture.controller()")
  public void beforeExecutionOfMethodDuplicate(JoinPoint joinPoint) {
    logger.info(
        "Enter: {}.{}() with argument[s] = {}",
        joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(),
        Arrays.toString(joinPoint.getArgs()));
  }
}
