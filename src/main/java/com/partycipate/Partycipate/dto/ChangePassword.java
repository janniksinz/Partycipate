package com.partycipate.Partycipate.dto;

public class ChangePassword {
    private String email;
    private String oldPW;
    private String newPW;

    public ChangePassword(String email, String oldPassword, String newPassword1, String newPassword2) {
        this.email = email;
        this.oldPW = oldPassword;
        this.newPW = newPassword1;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getOldPW() {
        return oldPW;
    }

    public void setOldPW(String oldPW) {
        this.oldPW = oldPW;
    }

    public String getNewPW() {
        return newPW;
    }

    public void setNewPW(String newPW) {
        this.newPW = newPW;
    }
}
