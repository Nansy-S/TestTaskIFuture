package com.prokopovich.balanceapi.webapp;

import com.prokopovich.balanceapi.webapp.util.RequestCounter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WebApp {

	public static void main(String[] args) {

		SpringApplication.run(WebApp.class, args);

		RequestCounter.countRequest(1000);
	}

}
