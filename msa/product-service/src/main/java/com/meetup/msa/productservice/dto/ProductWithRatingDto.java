package com.meetup.msa.productservice.dto;

import lombok.Data;


@Data
public class ProductWithRatingDto extends ProductDto {
    private Float rating;

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

}
