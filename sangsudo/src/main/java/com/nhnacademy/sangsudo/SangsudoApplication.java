package com.nhnacademy.sangsudo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SangsudoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SangsudoApplication.class, args);
	}

}
