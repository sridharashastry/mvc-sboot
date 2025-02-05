package com.bring.web.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class PaymentInfoAspect {



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
