package com.example.springcore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnBean({OrderReceiverBean.class, PaymentProcessBean.class})
public class OrderProcessorBean {

    private final OrderReceiverBean orderReceiverBean;
    private PaymentProcessBean paymentProcessorBean;

    @Autowired
    public OrderProcessorBean(OrderReceiverBean orderReceiverBean) {
        this.orderReceiverBean = orderReceiverBean;
    }

    @Autowired
    public void setPaymentProcessorBean(PaymentProcessBean paymentProcessorBean){
        this.paymentProcessorBean = paymentProcessorBean;
    }

    public void processOrder(){
        orderReceiverBean.receiveOrder();
        paymentProcessorBean.processPayment();
    }

}
