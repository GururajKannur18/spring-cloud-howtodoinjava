package com.meetup.msa.ratingservice.repository;

import com.meetup.msa.ratingservice.model.ProductRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cveerapaneni on 7/1/17.
 */
@Repository
public interface ProductRatingRepository extends CrudRepository<ProductRating, Long> {

    @Query("SELECT AVG(pr.rating) FROM ProductRating pr WHERE pr.productId = ?1")
    Float getAverageRating(final Long productId);
}
