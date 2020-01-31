package com.sample.common.dao.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "user_facility")
public class UserFacility {

    @EmbeddedId
    private UserFacilityPk userFacilityPk;

    @Version
    private int version;

    public UserFacilityPk getUserFacilityPk() {
        return userFacilityPk;
    }

    public void setUserFacilityPk(UserFacilityPk userFacilityPk) {
        this.userFacilityPk = userFacilityPk;
    }
}
