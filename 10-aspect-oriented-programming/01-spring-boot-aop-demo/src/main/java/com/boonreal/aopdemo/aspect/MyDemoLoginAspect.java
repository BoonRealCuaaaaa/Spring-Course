package com.boonreal.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoginAspect {

//    @Before("execution(public void addAccount())")
    @Before("execution(public * com.boonreal.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n==============>>>> Executing @Before advice on addAccount");
    }
}
