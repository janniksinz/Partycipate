package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.partycipate.Partycipate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Survey")
public class Survey {
    @Id
    @SequenceGenerator(
            name = "survey_sequence",
            sequenceName = "survey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    private String creation_date;
    private String title;
    private String cookie;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private Set<SurveyElement> elements = new HashSet<>() ;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Survey_Participant",joinColumns = @JoinColumn(name="survey_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="participant_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Participant> participantSet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public Survey(){}
    public Survey(@JsonProperty("creation_date") String creation_date,
                  @JsonProperty("title") String title,
                  @JsonProperty("cookie") String cookie,
                  @JsonProperty("user") User user
                  ){
         this.creation_date=creation_date; this.title=title;
        this.cookie=cookie; this.user=user;
    }
    /*start of Methods*/


    private Survey(Builder builder) {

        this.creation_date = builder.creation_date;
        this.title = builder.title;
        this.cookie = builder.cookie;


    }




    //custom constructor for optional parameters
    public static class Builder{

        private int id ;

        private String creation_date = "";
        private String title = "Survey";
        private String cookie = "";
        private ArrayList<SurveyElement> elements = null;

        public Builder id(int id){
            this.id=id;
            return this;
        }

        public Builder creation_date(String creation_date){
            this.creation_date=creation_date;
            return this;
        }
        public Builder title(String title){
            this.title =title;
            return this;
        }
        public Builder cookie(String cookie){
            this.cookie=cookie;
            return this;
        }
        public Builder elements(ArrayList<SurveyElement> elements){
            this.elements=elements;
            return this;
        }
        public Survey build(){
            return new Survey(this);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addElement(SurveyElement element){
        if(elements.contains(element))
            return;
        elements.add(element);

    }


    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }


    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }



    public String getCookie() {
        return cookie;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public Set<SurveyElement> getElements() {
        return elements;
    }

    public void setElements(Set<SurveyElement> elements) {
        this.elements = elements;
    }

    public void setParticipantSet(Set<Participant> participantSet) {
        this.participantSet = participantSet;
    }

    public Set<Participant> getParticipantSet() {
        return participantSet;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", creation_date='" + creation_date + '\'' +
                ", title='" + title + '\'' +
                ", cookie='" + cookie + '\'' +
                ", elements=" + elements +
                ", participantSet=" + participantSet +
                ", user=" + user +
                '}';
    }
}
