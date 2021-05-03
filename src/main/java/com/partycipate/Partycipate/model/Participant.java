package com.partycipate.Partycipate.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Participant {
    @Id
    @SequenceGenerator(
            name = "participant_sequence",
            sequenceName = "participant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participant_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )

    private int id;
    private String cookie;
    private String region;
    private String language;

    public Participant(String cookie, String region, String language) {
        this.cookie = cookie;
        this.region = region;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @ManyToMany(mappedBy = "participantSet")
    private Set<Survey> surveySet;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private Set<Answer> answerSet = new HashSet<>();

    private Participant(Builder builder) {
        this.cookie= builder.cookie;
        this.id= builder.id;
        this.region= builder.region;
    }

    public Participant() {}

    public static class Builder {

        private int id;

        private String cookie;

        private String region;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }



        public Participant build() {
            return new Participant(this);
        }
    }

    public Set<Answer> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<Answer> answerSet) {
        this.answerSet = answerSet;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getRegion() {
        return region;
    }



    public void setRegion(String region) {
        this.region = region;
    }

    public Set<Survey> getSurveySet() {
        return surveySet;
    }

    public void setSurveySet(Set<Survey> surveySet) {
        this.surveySet = surveySet;
    }

    @PreRemove
    private void removeParticipantsFromSurvey(){
        for (Survey s: surveySet) {
            s.getParticipantSet().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", cookie='" + cookie + '\'' +
                ", region='" + region + '\'' +
                ", surveySet=" + surveySet +
                ", answerSet=" + answerSet +
                '}';
    }


}