package com.partycipate.Partycipate.security.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class SignUpForm {
    private String username;
    private String email;
    private Set<String> role; //ROLE_ADMIN USER PARTICIPANT
    private String password;
    private String name;

    public SignUpForm(@JsonProperty("username") String username,
                      @JsonProperty("email") String email,
                      @JsonProperty("role") Set<String> role,
                      @JsonProperty("password") String password,
                      @JsonProperty("name") String name){
        this.username=username;
        this.email=email;
        this.role=role;
        this.password=password;
        this.name=name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {        return name;    }

    public void setName(String name) {        this.name = name;    }
}
