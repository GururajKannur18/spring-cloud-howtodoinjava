package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyClientSideController {
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/client/frontend")
	public String hi() {
		String randomString = this.restTemplate.getForObject("http://server/backend", String.class);
		System.out.println("RESPONSE = "+randomString);
		return "Server Response :: " + randomString;
	}
}
