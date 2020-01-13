package com.sample.application.query;

import com.sample.common.dao.entity.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 施設関連のデータ取得を行う
 */
@Component
public class FacilityQuery {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    FacilityQuery(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * ユーザーが属する全施設を取得
     *
     * @param userId
     * @return
     */
    public List<Facility> findByUserId(String userId) {

        StringBuilder builder = new StringBuilder();

        builder.append( "SELECT                              " );
        builder.append( "  f.facility_id                     " );
        builder.append( " ,f.facility_name                   " );
        builder.append( "FROM                                " );
        builder.append( "  facilities f                      " );
        builder.append( "INNER JOIN user_facility uf         " );
        builder.append( "  ON f.facility_id = uf.facility_id " );
        builder.append( "WHERE                               " );
        builder.append( "  uf.user_id = ?                    " );

//        // マッピングをラムダ式で定義する場合
//        return jdbcTemplate.query( builder.toString(), (rs, rowNum) -> {
//            Facility facility = new Facility();
//            facility.setFacilityId( rs.getInt( "facility_id" ) );
//            facility.setFacilityName( rs.getString( "facility_name" ) );
//            return facility;
//        }, userId );

        // BeanPropertyRowMapperで自動マッピング
        return jdbcTemplate.query(
                builder.toString(),
                new BeanPropertyRowMapper<Facility>( Facility.class ),
                userId );
    }
}
