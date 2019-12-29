package com.sample.app.query;

import com.sample.app.model.Facility;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
