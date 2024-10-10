package com.example.springcore;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {


    @Pointcut("execution( * com.example..*(..))")
    public void allPublicMethods(){}

    @Before("allPublicMethods()")
    public void logMethodStart(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Starting method : " + className + "." + methodName);

    }

    @After("allPublicMethods()")
    public void logMethodEnd(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Ending method : " + className + "." + methodName);
    }

}
