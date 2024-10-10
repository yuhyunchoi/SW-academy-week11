package com.example.springcore;

import org.springframework.stereotype.Component;

@Component
class DeliveryServiceBean {
    public void deliver(){
        System.out.println("deliver");
    }
}
