package com.meetup.msa.ratingservice.dto;

import lombok.Data;

/**
 * Created by cveerapaneni on 7/1/17.
 */
@Data
public class ProductRatingRequestDto {
	private Float rating;
	private String comments;

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
