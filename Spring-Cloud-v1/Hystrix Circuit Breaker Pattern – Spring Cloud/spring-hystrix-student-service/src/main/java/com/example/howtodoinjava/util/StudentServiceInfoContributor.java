package com.example.howtodoinjava.util;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;


@Component
public class StudentServiceInfoContributor implements InfoContributor{
	
	@Override
	public void contribute(Builder builder) {
		Map<String, String> singletonMap = Collections.singletonMap("details", 
				"This is the Student service, which is discovery server aware, and this service will be Called by school Microservice, "
				+ "from student details, which is again dicovery server aware!!!");
		builder.withDetail("details", singletonMap);
	}
}
