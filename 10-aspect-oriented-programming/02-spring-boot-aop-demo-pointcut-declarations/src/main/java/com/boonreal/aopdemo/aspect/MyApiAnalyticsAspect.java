package com.boonreal.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(5)
public class MyApiAnalyticsAspect {

    @Before("com.boonreal.aopdemo.aspect.AopExpression.forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n==============>>>> Executing @Before advice perform api analytics");
    }
}
