package com.staysbook.reviewsmanagement.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.staysbook.reviewsmanagement.dto.ReviewDTO;

@Repository
public class ReviewRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<ReviewDTO> findAverageCalificationsByStays(String idStays) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("GetAverageCalificationByStays")
				.declareParameters(new SqlParameter[] { new SqlParameter("i_vStayIds", Types.VARCHAR),
						new SqlParameter("o_rStays", Types.REF_CURSOR) })
				.returningResultSet("o_rStays", new RowMapper<ReviewDTO>() {
					@Override
					public ReviewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReviewDTO reviewByStay = new ReviewDTO();
						reviewByStay.setIdStay(rs.getLong(1));
						reviewByStay.setStayName(rs.getString(2));
						reviewByStay.setAverageRating(rs.getDouble(3));

						return reviewByStay;
					}
				});

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("i_vStayIds", idStays);

		Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);

		@SuppressWarnings("unchecked")
		List<ReviewDTO> reviewByStayList = (List<ReviewDTO>) results.get("o_rStays");

		return reviewByStayList;
	}
}
