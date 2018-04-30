package com.salesAnalyst.version1.SalesAnalyst.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String userType = "Normal";

    @NotNull
    private Integer accessStatus = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(Integer accessStatus) {
        this.accessStatus = accessStatus;
    }


}
