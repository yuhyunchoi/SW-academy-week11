package com.example.springcore;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

`@Component
@Slf4j
public class OrderReceiverBean {
    public void receiveOrder(){
        log.info("receiveOrder");
//        System.out.println("receiveOrder");

    }
}
