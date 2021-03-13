package com.partycipate.Partycipate.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;
    private String username;
    private String email;
    private String password;



    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.password= builder.password;

    }

    public User() {}

    public static class Builder {

        private int id = 0;
        private String username = "User";
        private String email = "user@email.de";
        private String password = "password";


        public User.Builder id(int id) {
            this.id = id;
            return this;
        }

        public User.Builder username(String username) {
            this.username = username;
            return this;
        }

        public User.Builder email(String email) {
            this.email = email;
            return this;
        }
        public User.Builder password(String password) {
            this.password = password;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}