package com.nhnacademy.sangsudo.aop;

import com.nhnacademy.sangsudo.service.AuthenticationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class CheckAOP {
    private static final String PRICE_LOG_FILE = "D:\\소프트웨어 아카데미\\11주차\\sangsudo\\src\\main\\resources\\price.log";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private AuthenticationService authenticationService;

    @Pointcut("execution(* com.nhnacademy.sangsudo.service.PriceService.*(..))")
    public void priceRequestPointcut() {}

    @Around("priceRequestPointcut()")
    public Object logPriceRequests(ProceedingJoinPoint joinPoint) throws Throwable {
        if (authenticationService.getLoggedInUser() == null) {
            throw new RuntimeException("User is not logged in.");
        }

        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        StringBuilder logMessage = new StringBuilder();

        logMessage.append("<----- ").append(authenticationService.getLoggedInUser().getName()).append(" ");
        logMessage.append("class ").append(joinPoint.getSignature().getDeclaringTypeName()).append(" ");
        logMessage.append("method ").append(methodName).append(" ");
        logMessage.append("parameters ").append(java.util.Arrays.toString(args));

        logToFile(logMessage.toString());

        return joinPoint.proceed();
    }

    public void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRICE_LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
