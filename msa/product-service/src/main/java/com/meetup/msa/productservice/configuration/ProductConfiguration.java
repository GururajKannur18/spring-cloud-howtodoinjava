package com.meetup.msa.productservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cveerapaneni on 7/1/17.
 */
@Configuration
public class ProductConfiguration {

    @LoadBalanced
    @Bean
    public RestOperations restTemplate() {
        return new RestTemplate();
    }
}
