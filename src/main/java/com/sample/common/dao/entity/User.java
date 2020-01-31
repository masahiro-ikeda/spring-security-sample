package com.sample.common.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name="user_id")
    private String userId;

    @Column
    private String password;

    @Column
    private String userName;

    @Column
    private String userRole;

    @Version
    private int version;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
