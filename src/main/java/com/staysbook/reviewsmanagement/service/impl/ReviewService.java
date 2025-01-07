package com.staysbook.reviewsmanagement.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staysbook.reviewsmanagement.dto.ReviewDTO;
import com.staysbook.reviewsmanagement.repository.IReviewJpaRepository;
import com.staysbook.reviewsmanagement.repository.ReviewRepository;
import com.staysbook.reviewsmanagement.service.IReviewService;

@Service
public class ReviewService implements IReviewService {

	@Autowired
	IReviewJpaRepository reviewJpaRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public List<ReviewDTO> getAverageCalificationsJpa(String idStays) {
		List<Long> idStaysListNumber = Arrays.asList(idStays.split(",")).stream().map(Long::valueOf)
				.collect(Collectors.toList());
		List<ReviewDTO> reviewsByStaysList = reviewJpaRepository.findAverageCalificationsByStays(idStaysListNumber);

		return reviewsByStaysList;
	}

	@Override
	public List<ReviewDTO> getAverageCalificationsProc(String idStays) {
		return reviewRepository.findAverageCalificationsByStays(idStays);
	}

}
