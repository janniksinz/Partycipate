package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminChangeUser {
    private int id;
    private String oldEmail;
    private String newEmail;
    private String oldName;
    private String newName;

    public AdminChangeUser(@JsonProperty("user_id") int id,
                           @JsonProperty("oldEmail") String oldEmail,
                           @JsonProperty("newEmail") String newEmail,
                           @JsonProperty("oldName") String oldName,
                           @JsonProperty("newName") String newName) {
        this.id = id;
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
        this.oldName = oldName;
        this.newName = newName;
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

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
