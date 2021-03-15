package com.partycipate.Partycipate.model;


import javax.persistence.*;
import javax.servlet.http.Part;
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
    private String ip_address;
    private String cookie;
    private String gender;
    private String region;
    private String age;
    private String email;

    @ManyToMany(mappedBy = "participantSet")
    private Set<Survey> surveySet;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    private Set<Answer> answerSet = new HashSet<>();

    private Participant(Builder builder) {
        this.age=builder.age;
        this.cookie= builder.cookie;
        this.email= builder.email;
        this.gender=builder.gender;
        this.id= builder.id;
        this.ip_address= builder.ip_address;
        this.region= builder.region;
    }

    public Participant() {}

    public static class Builder {

        private int id;
        private String ip_address;
        private String cookie;
        private String gender;
        private String region;
        private String age;
        private String email;


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

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getCookie() {
        return cookie;
    }

    public String getIp_address() {
        return ip_address;
    }

    public String getGender() {
        return gender;
    }

    public String getRegion() {
        return region;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
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
}