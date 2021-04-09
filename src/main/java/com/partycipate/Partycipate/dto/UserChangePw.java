package com.partycipate.Partycipate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserChangePw {
    String oldPw;
    String newPw;

    public UserChangePw(@JsonProperty("oldPw") String oldPw,
                        @JsonProperty("newPw") String newPw) {
        this.oldPw = oldPw;
        this.newPw = newPw;
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
