package com.example.springcore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderProcessorBean {

    private final OrderReceiveBean orderReceiverBean;
    private PaymentProcessorBean paymentProcessorBean;

    @Autowired
    public OrderProcessorBean(OrderReceiveBean orderReceiverBean) {
        this.orderReceiverBean = orderReceiverBean;
    }

    @Autowired
    public void setPaymentProcessorBean(PaymentProcessorBean paymentProcessorBean){
        this.paymentProcessorBean = paymentProcessorBean;
    }

    public void processOrder(){
        orderReceiverBean.receiveOrder();
        paymentProcessorBean.processPayment();
    }

}
