package com.sample.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private String authority;
    private String facilityGroup;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getFacilityGroup() {
        return facilityGroup;
    }

    public void setFacilityGroup(String facilityGroup) {
        this.facilityGroup = facilityGroup;
    }
}
