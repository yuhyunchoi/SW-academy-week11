package com.nhnacademy.sangsudo;

import com.nhnacademy.sangsudo.account.Account;
import com.nhnacademy.sangsudo.aop.CheckAOP;
import com.nhnacademy.sangsudo.aop.LoginAOP;
import com.nhnacademy.sangsudo.dataparser.DataParser;
import com.nhnacademy.sangsudo.formatter.OutputFormatter;
import com.nhnacademy.sangsudo.price.Price;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nhnacademy.sangsudo.*;
import com.nhnacademy.sangsudo.service.PriceService;
import com.nhnacademy.sangsudo.service.AuthenticationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
@SpringBootTest
class SangsudoApplicationTests {

	@Mock
	private DataParser dataParser;

	@Mock
	private OutputFormatter outputFormatter;

	@Mock
	private AuthenticationService authenticationService;

	@Spy
	private PriceService priceService;

	@InjectMocks
	private CheckAOP checkAOP;

	@InjectMocks
	private LoginAOP loginAOP;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	// 예외 상황 테스트: 로그인하지 않은 경우 AOP가 예외를 던지는지 확인
	@Test
	public void testCheckAOP_WhenUserNotLoggedIn_ShouldThrowException() throws Throwable {
		ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
		when(authenticationService.getLoggedInUser()).thenReturn(null);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			checkAOP.logPriceRequests(joinPoint);
		});

		assertEquals("User is not logged in.", exception.getMessage());
	}

	// 정상 흐름 테스트: 로그인된 사용자가 PriceService 호출 시 로그가 기록되는지 확인
	@Test
	public void testCheckAOP_WhenUserLoggedIn_ShouldProceed() throws Throwable {
		ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
		Account loggedInUser = new Account("user123", "password", "John Doe");

		// Mock the user as logged in
		when(authenticationService.getLoggedInUser()).thenReturn(loggedInUser);
		when(joinPoint.proceed()).thenReturn(null);

		// Spy on the CheckAOP object to intercept the logToFile method
		doNothing().when(checkAOP).logToFile(anyString());

		// Execute the method
		checkAOP.logPriceRequests(joinPoint);

		// Verify that the logToFile method was called once with the correct log message
		verify(checkAOP, times(1)).logToFile(contains("John Doe"));
	}

	// PriceService의 price() 메서드 테스트: 도시/섹터에 맞는 Price 객체를 찾지 못했을 때
	@Test
	public void testPriceService_WhenPriceNotFound_ShouldReturnNull() {
		when(dataParser.price("CityX", "SectorY")).thenReturn(null);

		Price price = priceService.price("CityX", "SectorY");

		assertNull(price);
	}

	// PriceService의 price() 메서드 정상 흐름 테스트: 가격을 제대로 반환하는지 확인
	@Test
	public void testPriceService_WhenPriceFound_ShouldReturnCorrectPrice() {
		Price expectedPrice = new Price("CityX", "SectorY", 100);
		when(dataParser.price("CityX", "SectorY")).thenReturn(expectedPrice);

		Price price = priceService.price("CityX", "SectorY");

		assertNotNull(price);
		assertEquals("CityX", price.getCity());
		assertEquals("SectorY", price.getSector());
		assertEquals(100, price.getUnitPrice());
	}

	// 예외 상황 테스트: 로그인 시 잘못된 ID나 비밀번호를 입력한 경우
	@Test
	public void testAuthenticationService_WhenInvalidCredentials_ShouldReturnErrorMessage() {
		Account account = new Account("user123", "password", "John Doe");
		List<Account> accounts = List.of(account);

		String result = authenticationService.login("wrongID", "wrongPassword", accounts);

		assertEquals("id or password not correct", result);
	}

	// 정상 흐름 테스트: 올바른 자격증명으로 로그인한 경우
	@Test
	public void testAuthenticationService_WhenValidCredentials_ShouldReturnAccount() {
		Account account = new Account("user123", "password", "John Doe");
		List<Account> accounts = List.of(account);

		String result = authenticationService.login("user123", "password", accounts);

		assertEquals("Account(id=user123, password=password, name=John Doe)", result);
	}

	// 예외 상황 테스트: 로그아웃을 시도할 때, 로그인된 사용자가 없는 경우
	@Test
	public void testAuthenticationService_WhenLogoutWithoutLogin_ShouldReturnErrorMessage() {
		when(authenticationService.getLoggedInUser()).thenReturn(null);

		String result = authenticationService.logout();

		assertEquals("No user in currently logged in", result);
	}

	// 정상 흐름 테스트: 로그아웃이 정상적으로 되는 경우
	@Test
	public void testAuthenticationService_WhenUserLoggedOut_ShouldReturnGoodBye() {
		Account loggedInUser = new Account("user123", "password", "John Doe");
		when(authenticationService.getLoggedInUser()).thenReturn(loggedInUser);

		String result = authenticationService.logout();

		assertEquals("Good Bye", result);
	}

}
