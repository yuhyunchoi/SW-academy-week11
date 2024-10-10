package com.example.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    private final OrderProcessorBean orderProcessorBean;
    private final ChefBean chefBean;
    private final DeliveryServiceBean deliveryServiceBean;

    @Autowired
    public AppStartupRunner(OrderProcessorBean orderProcessorBean, ChefBean chefBean, DeliveryServiceBean deliveryServiceBean) {
        this.orderProcessorBean = orderProcessorBean;
        this.chefBean = chefBean;
        this.deliveryServiceBean = deliveryServiceBean;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Starting the order processing system...");

        // Order processing
        orderProcessorBean.processOrder();

        // Cooking
        chefBean.cook();

        // Delivery
        deliveryServiceBean.deliver();

        System.out.println("Order processing complete.");
    }
}
