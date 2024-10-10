package com.example.springcore;

import com.example.springcore.chef.ChefBean;
import com.example.springcore.chef.ChefBeanEng;
import com.example.springcore.deliver.DeliveryServiceBeanEng;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {
    private final ProcessorBean processorBean;
    private final List<ChefBean> chefBeans;
    private final DeliveryServiceBeanEng deliveryServiceBeanEng;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println(processorBean.getOrderProcessorBean());
        System.out.println(processorBean.getPaymentProcessorBean());


        for (ChefBean chef : chefBeans) {
            chef.cook();  // 각각의 ChefBean 실행
        }
        deliveryServiceBeanEng.deliver();

    }

}
