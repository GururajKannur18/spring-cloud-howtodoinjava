package com.meetup.msa.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.meetup.msa.productservice.dto.ProductWithRatingDto;
import com.meetup.msa.productservice.model.Product;
import com.meetup.msa.productservice.repository.ProductRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final RestOperations restTemplate;

    @Autowired
    public ProductService(final ProductRepository productRepository, @LoadBalanced final RestOperations restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public ProductWithRatingDto getProductDetails(final Long productId) {
        final Product product = productRepository.findOne(productId);
        // Create ProductWithRatingDto
        final ProductWithRatingDto response = new ProductWithRatingDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
//        response.setRating(getProductRating(productId));

        return response;
    }

    @HystrixCommand(fallbackMethod = "onCircuitOpenGetProductRating")
    public Float getProductRating(final Long productId) {
        // Get the ratings from the Rating service

        final String url = "http://rating-service/ratings/products/" + productId;
        final ResponseEntity<Float> ratingResponse = restTemplate.exchange(url, HttpMethod.GET, null, Float.class);
        return ratingResponse.getBody();
    }

    private Float onCircuitOpenGetProductRating(final Long productId) {
        return 0.0F;
    }
}
