package com.homemanagment.homemanagment.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class BookAspect {
    @Before("execution(* com.homemanagment.homemanagment.service.BookServiceImpl.*(..))")
    public void logBookInfoBeforeExecuteServiceMethod(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("Log before %s with args: %s\n");
        joinPoint.getSignature();
        Arrays.toString(args);
    }

    @After("execution(* com.homemanagment.homemanagment.repositories.BookDao.*(..))")
    public void logBookInfoAfterExecuteServiceMethod(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("Book method executed");
        joinPoint.getSignature();
        Arrays.toString(args);
    }

}
