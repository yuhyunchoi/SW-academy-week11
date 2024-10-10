package com.example.springcore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentProcessBean {

    public void processPayment(){
        log.info("processPayment");
//        System.out.println("processPayment");
    }
}
