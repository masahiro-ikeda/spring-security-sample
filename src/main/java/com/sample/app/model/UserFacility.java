package com.sample.app.model;

import javax.persistence.*;

@Entity
@Table(name = "user_facility")
public class UserFacility {

    @EmbeddedId
    private UserFacilityPk userFacilityPk;

    public UserFacilityPk getUserFacilityPk() {
        return userFacilityPk;
    }

    public void setUserFacilityPk(UserFacilityPk userFacilityPk) {
        this.userFacilityPk = userFacilityPk;
    }
}
