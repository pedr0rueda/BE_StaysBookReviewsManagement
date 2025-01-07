package com.staysbook.reviewsmanagement.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ReviewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("IdStay")
	private Long idStay;

	@JsonProperty("StayName")
	private String stayName;
	
	@JsonProperty("AverageRating")
	private Double averageRating;
	
}
