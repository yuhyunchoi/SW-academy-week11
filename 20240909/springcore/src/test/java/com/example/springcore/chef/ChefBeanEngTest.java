package com.example.springcore.chef;

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
class ChefBeanEngTest {

    @Mock
    Logger log;

    @InjectMocks
    ChefBeanEng chefBeanEng;

    @Test
    void testCookWithMessageEng() {
        // Given: chef.cook 프로퍼티 값 설정
        String cookMessage = "English food is being cooked";
        chefBeanEng = new ChefBeanEng();
        chefBeanEng.cook = cookMessage;

        // When: cook 메소드 호출
        chefBeanEng.cook();

        // Then: 로그에 올바르게 메시지가 기록되는지 확인
        verify(log, times(1)).info(cookMessage);
    }

    @Test
    void testCookWithNullMessage() {
        // Given: cook 프로퍼티 값이 null인 경우
        chefBeanEng = new ChefBeanEng();
        chefBeanEng.cook = null;

        // When: cook 메소드 호출
        chefBeanEng.cook();

        // Then: 로그 호출이 발생하지 않는지 검증
        verify(log, never()).info(anyString());
    }

    @Test
    void testConstructorInjection() {
        // Given: 생성자가 정상적으로 주입되었는지 확인
        assertNotNull(chefBeanEng);
    }
}
