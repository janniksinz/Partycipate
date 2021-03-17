package com.partycipate.Partycipate.model;

import javax.persistence.*;

@Entity
public class AuthToken {
    @Id
    private String Token;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
