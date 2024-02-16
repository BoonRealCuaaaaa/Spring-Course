package com.boonreal.aopdemo.aspect;

import com.boonreal.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(10)
public class MyDemoLoginAspect {

    @Around("execution(* com.boonreal.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint
    ) throws Throwable {
        String method=proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @After (finally) on method: "+method);

        long begin=System.currentTimeMillis();

        Object result=null;
        try{
            result=proceedingJoinPoint.proceed();

        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            result="Major accident!";
        }

        long end=System.currentTimeMillis();

        long duration=end-begin;
        System.out.println("\n=====> Duration: "+duration/1000.0+" seconds");
        return result;
    }

    @After("execution(* com.boonreal.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){
        String method=joinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @After (finally) on method: "+method);
    }


    @AfterThrowing(
            pointcut = "execution(* com.boonreal.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,Throwable exc){
        String method=joinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @AfterThrowing on method: "+method);

        System.out.println("\n===> The exception is: "+exc);
    }

    @AfterReturning(
            pointcut = "execution(* com.boonreal.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result){
        String method=joinPoint.getSignature().toShortString();
        System.out.println("\n====> Executing @AfterReturning on method: "+method);

        System.out.println("\n=====> Result is: "+result);

        convertAccountNameToUpperCase(result);

        Account account=result.get(0);
        account.setName(account.getName().toLowerCase());
    }

    private void convertAccountNameToUpperCase(List<Account> result) {
        for(Account account:result) {
            String upperName=account.getName().toUpperCase();
            account.setName(upperName);
        }
    }

    @Before("com.boonreal.aopdemo.aspect.AopExpression.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n==============>>>> Executing @Before advice on addAccount");

        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        System.out.println(methodSignature);

        Object[] args=joinPoint.getArgs();

        for(Object tempArg:args) {
            System.out.println(tempArg);

            if(tempArg instanceof Account account) {
                System.out.println(account);
            }
        }

    }
}
