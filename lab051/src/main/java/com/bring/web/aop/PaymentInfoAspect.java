package com.bring.web.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class PaymentInfoAspect {



    @Around("execution(* com.bring.web.controllers.PaymentsController.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();  // Executes the actual method

        long duration = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + duration + " ms");

        return result;
    }







    ///// Usage of Pointcut with Before and After
    /*
    @Pointcut("execution(* com.bring.web.controllers.PaymentsController.*(..))")
    public void paymentMethods() {
        // Pointcut method, no implementation needed
    }

    @Before("paymentMethods()")
    public void _beforeAdvise(JoinPoint joinPoint) {
        System.out.println("Payment method " + joinPoint.getSignature() + " initiated at: " + new Date());
    }

    @After("paymentMethods()")
    public void _afterAdvise(JoinPoint joinPoint) {
        System.out.println("Payment method " + joinPoint.getSignature() + " concluded at: " + new Date());
    }
*/



    //////////////////Previous implementation without Pointcut
    /*
    @Before("execution(* com.bring.web.controllers.PaymentsController.*(..))")

    public void _beforeAdvise(JoinPoint joinPoint){

        System.out.println("Payment method "+joinPoint.getSignature()+" initiated at :"+new Date());

    }


    @After("execution(* com.bring.web.controllers.PaymentsController.*(..))")

    public void _afterAdvise(JoinPoint joinPoint){

        System.out.println("Payment method "+joinPoint.getSignature()+" concluded at :"+new Date());

    }
    */




}
