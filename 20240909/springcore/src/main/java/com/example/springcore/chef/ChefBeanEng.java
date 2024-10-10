package com.example.springcore.chef;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("eng")
@Component
@Slf4j
public class ChefBeanEng implements ChefBean {
    @Value("${chef.cook}")
    private String cook;
    public void cook(){
        log.info(cook);
//        System.out.println(cook);
    }
}
