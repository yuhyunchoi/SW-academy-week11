package com.example.springcore.chef;

import com.example.springcore.deliver.DeliveryServiceProperties;
import org.junit.jupiter.api.BeforeEach;
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
class ChefBeanKorTest {

    @Mock
    DeliveryServiceProperties deliveryServiceProperties;

    @InjectMocks
    ChefBeanKor chefBeanKor;

    @Mock
    Logger log;

    @BeforeEach
    void setup() {
        log = LoggerFactory.getLogger(ChefBeanKor.class);
    }

    @Test
    void testCookWithMessageKor() {
        // Given: deliveryServiceProperties.getMessageKor() 설정
        when(deliveryServiceProperties.getMessageKor()).thenReturn("Korean food is being cooked");

        // When: chefBeanKor의 cook 메소드 실행
        chefBeanKor.cook();

        // Then: deliveryServiceProperties.getMessageKor() 호출 여부와 log 확인
        verify(deliveryServiceProperties, times(1)).getMessageKor();
        verify(log, times(1)).info("Korean food is being cooked");
    }

    @Test
    void testCookWithNullMessage() {
        // Given: deliveryServiceProperties.getMessageKor()이 null일 때
        when(deliveryServiceProperties.getMessageKor()).thenReturn(null);

        // When: chefBeanKor의 cook 메소드 실행
        chefBeanKor.cook();

        // Then: 로그가 호출되지 않거나, 적절한 에러 처리 여부 확인
        verify(deliveryServiceProperties, times(1)).getMessageKor();
        verify(log, never()).info(anyString());
    }

    @Test
    void testConstructorInjection() {
        // Given: deliveryServiceProperties가 올바르게 주입되는지 확인
        ChefBeanKor chefBeanKor = new ChefBeanKor(deliveryServiceProperties);
        assertNotNull(chefBeanKor);
    }
}
