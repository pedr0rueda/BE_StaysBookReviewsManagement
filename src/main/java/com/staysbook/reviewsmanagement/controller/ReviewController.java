package com.staysbook.reviewsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staysbook.reviewsmanagement.service.impl.ReviewService;
import com.staysbook.reviewsmanagement.dto.ReviewDTO;
import com.staysbook.reviewsmanagement.response.ErrorResponse;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RequestMapping("v1/stayBookReviewManagement")
@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/reviewsJpa/{idStays}")
	public ResponseEntity<?> getServicesByStaysJpa(@PathVariable(value = "idStays") String idStays) {
		try {
			List<ReviewDTO> response = reviewService.getAverageCalificationsJpa(idStays);
			
			if (response.isEmpty()) {
				return new ResponseEntity<>(new ErrorResponse(404, "No se encontraron registros"), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse(500, "Error interno del servidor. " + e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/reviewsProc/{idStays}")
	public ResponseEntity<?> getServicesByStaysProc(@PathVariable String idStays) {
		try {
			List<ReviewDTO> response = reviewService.getAverageCalificationsProc(idStays);
			
			if (response.isEmpty()) {
				return new ResponseEntity<>(new ErrorResponse(404, "No se encontraron registros"), HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse(500, "Error interno del servidor. " + e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
