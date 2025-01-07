package com.staysbook.reviewsmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.staysbook.reviewsmanagement.dto.ReviewDTO;
import com.staysbook.reviewsmanagement.entity.ReviewEntity;

@Repository
public interface IReviewJpaRepository extends JpaRepository<ReviewEntity, Long> {

	@Query("SELECT new com.staysbook.reviewsmanagement.dto.ReviewDTO "
			+ "( st.idStay, st.name, MAX(re.rating) ) "
			+ "FROM StayEntity st "
			+ "INNER JOIN StayReviewEntity sr ON sr.idStay   = st.idStay "
			+ "INNER JOIN ReviewEntity     re ON re.idReview = sr.idReview "
			+ "WHERE st.idStay IN :idStays "
			+ "GROUP BY st.idStay, st.name")
	List<ReviewDTO> findAverageCalificationsByStays(@Param("idStays") List<Long> idStays);

}
