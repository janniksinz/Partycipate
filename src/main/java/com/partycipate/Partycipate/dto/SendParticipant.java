package com.partycipate.Partycipate.dto;

public class SendParticipant {
    private int participant_id;
    private String participant_cookie;

    public SendParticipant() {

    }

    public SendParticipant(int participant_id, String cookie) {
        this.participant_id = participant_id;
        this.participant_cookie = cookie;
    }

    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }


    public String getParticipant_cookie() {
        return participant_cookie;
    }

    public void setParticipant_cookie(String participant_cookie) {
        this.participant_cookie = participant_cookie;
    }
}
