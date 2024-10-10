package com.example.springcore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("processor")
public class ProcessorBean {
    private String paymentProcessorBean;
    private String orderProcessorBean;

}
