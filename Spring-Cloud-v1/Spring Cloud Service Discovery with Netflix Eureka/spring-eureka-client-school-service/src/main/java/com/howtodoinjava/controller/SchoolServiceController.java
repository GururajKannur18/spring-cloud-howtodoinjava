package com.howtodoinjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SchoolServiceController {
	private static final String URL = "http://student-service/getStudentDetailsForSchool/{schoolname}";
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/getSchoolDetails/{schoolname}")
	public String getStudents(@PathVariable String schoolname) {
		System.out.println("Getting School details for " + schoolname);

		String response = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, schoolname).getBody();

		System.out.println("Response Received as " + response);

		return "School Name -  " + schoolname + " \n Student Details " + response;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}