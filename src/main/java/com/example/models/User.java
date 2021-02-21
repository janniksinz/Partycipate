package com.example.models;

public class User {
    private int id;
    private String name;
    private String email;

    // Constuctor
    public User(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    // Getter / Setter
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
