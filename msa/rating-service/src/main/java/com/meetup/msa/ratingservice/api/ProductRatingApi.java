package com.meetup.msa.ratingservice.api;

import com.meetup.msa.ratingservice.dto.ProductRatingRequestDto;
import com.meetup.msa.ratingservice.model.ProductRating;
import com.meetup.msa.ratingservice.repository.ProductRatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cveerapaneni on 7/1/17.
 */
@RequestMapping("/ratings/products/{productId}")
@RestController
public class ProductRatingApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRatingApi.class);
    private ProductRatingRepository productRatingRepository;

    @Autowired
    public ProductRatingApi(final ProductRatingRepository productRatingRepository) {
        this.productRatingRepository = productRatingRepository;
    }

    @PostMapping
    public Long rateProduct(final @PathVariable("productId") Long productId, @RequestBody ProductRatingRequestDto request) {
        final ProductRating pr = new ProductRating();
        pr.setProductId(productId);
        pr.setRating(request.getRating());
        pr.setComments(request.getComments());

        final ProductRating createdProductRating = productRatingRepository.save(pr);
        return createdProductRating.getId();
    }

    @GetMapping
    public Float getAverageProductRating(final @PathVariable("productId") Long productId) {
        LOGGER.info(">>>>>>>>>>> - Rating product - " + productId);
        return productRatingRepository.getAverageRating(productId);
    }
}
