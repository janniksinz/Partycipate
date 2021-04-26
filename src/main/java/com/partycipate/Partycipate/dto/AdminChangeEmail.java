package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminChangeEmail {
    private int id;
    private String oldEmail;
    private String newEmail;

    public AdminChangeEmail(@JsonProperty("user_id") int id,
                            @JsonProperty("oldEmail") String oldEmail,
                            @JsonProperty("newEmail") String newEmail) {
        this.id = id;
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
