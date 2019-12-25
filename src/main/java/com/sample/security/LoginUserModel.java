package com.sample.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class LoginUserModel extends User {

    private String facilityId;

    LoginUserModel(String username, String password, List<? extends GrantedAuthority> authorities, String facilityId) {
        super(username, password, authorities);
        this.facilityId = facilityId;
    }

    public String getFacilityId() {
        return facilityId;
    }
}
