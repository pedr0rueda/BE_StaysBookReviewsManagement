package com.staysbook.reviewsmanagement.service;

import java.util.List;

import com.staysbook.reviewsmanagement.dto.ReviewDTO;

public interface IReviewService {

	public List<ReviewDTO> getAverageCalificationsJpa(String idStays);

	public List<ReviewDTO> getAverageCalificationsProc(String idStays);
}
