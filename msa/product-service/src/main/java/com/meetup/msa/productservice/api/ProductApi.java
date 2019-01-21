package com.meetup.msa.productservice.api;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.meetup.msa.productservice.dto.ProductDto;
import com.meetup.msa.productservice.dto.ProductRequestDto;
import com.meetup.msa.productservice.dto.ProductWithRatingDto;
import com.meetup.msa.productservice.model.Product;
import com.meetup.msa.productservice.repository.ProductRepository;
import com.meetup.msa.productservice.service.ProductService;

/**
 * Created by cveerapaneni on 7/1/17.
 */
@RequestMapping("/products")
@RestController
public class ProductApi {

    private final ProductService productService;
    private ProductRepository productRepository;
    private RestOperations restTemplate;

    @Autowired
    public ProductApi(final ProductService productService, final ProductRepository productRepository, @LoadBalanced final RestOperations restTemplate) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public Collection<ProductDto> getAllProducts() {
        final Iterable<Product> allProducts = productRepository.findAll();
        
        final Collection<ProductDto> products = new LinkedHashSet<>();

        if (allProducts != null && allProducts.iterator().hasNext()) {
            for (final Product product : allProducts) {
                final ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setDescription(product.getDescription());
                products.add(productDto);
            }
        }
        return products;
    }

    @PostMapping
    public Long createProduct(final @RequestBody ProductRequestDto createProductRequest) {
        final Product p = new Product();
        p.setName(createProductRequest.getName());
        p.setDescription(createProductRequest.getDescription());

        final Product createdProduct = productRepository.save(p);
        return createdProduct.getId();
    }

    @GetMapping("/{productId}")
    public ProductWithRatingDto getProductDetails(@PathVariable("productId") final Long productId) {

        final ProductWithRatingDto productWithRatingDto = productService.getProductDetails(productId);

        productWithRatingDto.setRating(productService.getProductRating(productId));
        return productWithRatingDto;

        //        final Product product = productRepository.findOne(productId);
        //        // Create ProductWithRatingDto
        //        final ProductWithRatingDto response = new ProductWithRatingDto();
        //        response.setId(product.getId());
        //        response.setName(product.getName());
        //        response.setDescription(product.getDescription());
        //
        //        // Get the ratings from the Rating service
        //
        //        final String url = "http://rating-service/ratings/products/" + productId;
        //        final ResponseEntity<Float> ratingResponse = restTemplate.exchange(url, HttpMethod.GET, null, Float.class);
        //        response.setRating(ratingResponse.getBody());
        //        return response;
    }
}
