package com.example.springcore.deliver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceBeanEngTest {

    @Mock
    DeliveryServiceProperties deliveryServiceProperties;

    @InjectMocks
    DeliveryServiceBeanEng deliveryServiceBeanEng;

    @Mock
    Logger log;

    @Test
    void testDeliverWithMessageEng() {
        // Given: deliveryServiceProperties.getMessageEng() 설정
        when(deliveryServiceProperties.getMessageEng()).thenReturn("Delivery in English");

        // When: deliveryServiceBeanEng의 deliver 메소드 실행
        deliveryServiceBeanEng.deliver();

        // Then: deliveryServiceProperties.getMessageEng() 호출 여부와 log 확인
        verify(deliveryServiceProperties, times(1)).getMessageEng();
        verify(log, times(1)).info("Delivery in English");
    }

    @Test
    void testDeliverWithNullMessage() {
        // Given: deliveryServiceProperties.getMessageEng()이 null일 때
        when(deliveryServiceProperties.getMessageEng()).thenReturn(null);

        // When: deliveryServiceBeanEng의 deliver 메소드 실행
        deliveryServiceBeanEng.deliver();

        // Then: 로그가 호출되지 않거나, 적절한 에러 처리 여부 확인
        verify(deliveryServiceProperties, times(1)).getMessageEng();
        verify(log, never()).info(anyString());
    }

    @Test
    void testConstructorInjection() {
        // Given: deliveryServiceProperties가 올바르게 주입되는지 확인
        DeliveryServiceBeanEng deliveryServiceBeanEng = new DeliveryServiceBeanEng(deliveryServiceProperties);
        assertNotNull(deliveryServiceBeanEng);
    }
}
