package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminChangePw {
    private int id;
    private String newPw;

    public AdminChangePw(@JsonProperty("user_id") int id,
                         @JsonProperty("newPw") String newPw) {
        this.id = id;
        this.newPw = newPw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }
}
