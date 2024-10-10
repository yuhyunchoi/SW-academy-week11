package com.example.springcore.deliver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("delivery.service")
public class DeliveryServiceProperties {

    private String messageEng;
    private String messageKor;

    public String getMessageEng() {
        return messageEng;
    }

    public void setMessageEng(String messageEng) {
        this.messageEng = messageEng;
    }

    public String getMessageKor() {
        return messageKor;
    }

    public void setMessageKor(String messageKor) {
        this.messageKor = messageKor;
    }
}
