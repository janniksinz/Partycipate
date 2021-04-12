package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminChangePw {
    private String email;
    private String oldPw;
    private String newPw;

    public AdminChangePw(@JsonProperty("email") String email,
                         @JsonProperty("oldPw") String oldPw,
                         @JsonProperty("newPw") String newPw) {
        this.email = email;
        this.oldPw = oldPw;
        this.newPw = newPw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPw() {
        return oldPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }
}
