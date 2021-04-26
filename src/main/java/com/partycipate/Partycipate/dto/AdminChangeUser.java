package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminChangeUser {
    private int id;
    private String email;
    private String name;

    public AdminChangeUser(@JsonProperty("user_id") int id,
                           @JsonProperty("email") String email,
                           @JsonProperty("name") String name){
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
