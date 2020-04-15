package com.best.spring.aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemArchitecture {

  @Pointcut("execution (* (@org.springframework.stereotype.Repository *).*(..))")
  public void repository() {}

  @Pointcut("execution (* (@org.springframework.stereotype.Service *).*(..))")
  public void service() {}

  @Pointcut("execution (* (@org.springframework.web.bind.annotation.RestController *).*(..))")
  public void controller() {}
}
