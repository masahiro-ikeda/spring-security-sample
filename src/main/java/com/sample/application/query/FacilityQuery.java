package com.sample.application.query;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.sample.common.model.Facility;

@Mapper
public interface FacilityQuery {

	@Select(" SELECT " +
			"   f.facility_id AS facilityId, " +
			"   f.facility_name AS facilityName" +
			" FROM " +
			"   facilities f " +
			" INNER JOIN user_facility uf " +
			"   ON f.facility_id = uf.facility_id " +
			" WHERE " +
			"   uf.user_id = #{userId}")
	List<Facility> findByUserId(@Param("userId") String userId);
}
