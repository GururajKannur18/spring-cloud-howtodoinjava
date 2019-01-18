package com.howtodoinjava.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigClientApplication.class, args);
	}
}


@RestController
@RefreshScope
class MessageController{
	@Value("${msg:Hello world - Config Server is not working..pelase check}")
	private String msg;
	
	@GetMapping("/msg")
	public String message() {
		return this.msg;
	}
}

