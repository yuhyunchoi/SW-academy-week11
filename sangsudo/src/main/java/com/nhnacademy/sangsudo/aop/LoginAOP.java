package com.nhnacademy.sangsudo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoginAOP {
    private static final String ACCOUNT_LOG_FILE = "D:\\소프트웨어 아카데미\\11주차\\sangsudo\\src\\main\\resources\\account.log";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Pointcut("execution(* com.nhnacademy.sangsudo.service.AuthenticationService.login(..)) || execution(* com.nhnacademy.sangsudo.service.AuthenticationService.logout(..))")
    public void authenticationPointcut() {}

    @AfterReturning(pointcut = "authenticationPointcut()", returning = "result")
    public void logAuthenticationRequests(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        StringBuilder logMessage = new StringBuilder();

        logMessage.append(LocalDateTime.now().format(DATE_TIME_FORMATTER)).append(" INFO [main] ");
        logMessage.append("class ").append(joinPoint.getSignature().getDeclaringTypeName()).append(" ");
        logMessage.append("method ").append(methodName).append(" ");
        logMessage.append("parameters ").append(java.util.Arrays.toString(args)).append(" ");
        logMessage.append("result ").append(result);

        logToFile(logMessage.toString());
    }

    public void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

