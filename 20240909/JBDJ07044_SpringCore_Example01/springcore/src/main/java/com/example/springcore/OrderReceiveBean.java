package com.example.springcore;


import org.springframework.stereotype.Component;

@Component
class OrderReceiveBean {
    public void receiveOrder(){
        System.out.println("receiverOrder");
    }
}
