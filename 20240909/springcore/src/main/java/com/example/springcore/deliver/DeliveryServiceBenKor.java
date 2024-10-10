package com.example.springcore.deliver;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!eng")
@Component
@Slf4j
public class DeliveryServiceBenKor implements DeliveryServiceBean{

    private final DeliveryServiceProperties deliveryServiceProperties;

    public DeliveryServiceBenKor(DeliveryServiceProperties deliveryServiceProperties) {
        this.deliveryServiceProperties = deliveryServiceProperties;
    }

    public void deliver(){
        log.info(deliveryServiceProperties.getMessageKor());
//        System.out.println(deliveryServiceProperties.getMessageKor());
    }
}
