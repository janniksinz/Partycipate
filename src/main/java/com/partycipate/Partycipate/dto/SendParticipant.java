package com.partycipate.Partycipate.dto;

public class SendParticipant {
    private int participant_id;
    private String cookie;

    public SendParticipant() {

    }

    public SendParticipant(int participant_id, String cookie) {
        this.participant_id = participant_id;
        this.cookie = cookie;
    }

    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
