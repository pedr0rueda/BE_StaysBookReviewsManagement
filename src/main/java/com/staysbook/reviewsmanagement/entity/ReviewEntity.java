package com.staysbook.reviewsmanagement.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "REVIEW")
public class ReviewEntity {

	@Basic(optional = false)
	@Column(name = "ID_REVIEW")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long idReview;

	@Basic(optional = false)
	@Column(name = "RATING")
	@NotNull
	@Size(min = 1, max = 3)
	private Double rating;

	@Basic(optional = false)
	@Column(name = "DESCRIPTION")
	@NotNull
	@Size(min = 1, max = 50)
	private String description;

}
