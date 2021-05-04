package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyElement> elements = new ArrayList<>() ;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "survey_participant",joinColumns = @JoinColumn(name="survey_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="participant_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Participant> participantSet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public Survey(){}
    public Survey(@JsonProperty("creation_date") String creation_date,
                  @JsonProperty("title") String title,
                  @JsonProperty("user") User user
                  ){
         this.creation_date=creation_date; this.title=title;
        this.user=user;
    }
    /*start of Methods*/


    private Survey(Builder builder) {


        this.creation_date = builder.creation_date;
        this.title = builder.title;
        this.elements= builder.elements;
        this.user=builder.user;



    }




    //custom constructor for optional parameters
    public static class Builder{

        private int id ;

        private String creation_date = "";
        private String title = "Survey";
        private List<SurveyElement> elements = null;
        private User user;

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

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder elements(List<SurveyElement> elements){
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




    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }


    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }




    public String getCreation_date() {
        return creation_date;
    }

    public List<SurveyElement> getElements() {
        return elements;
    }

    public void setElements(List<SurveyElement> elements) {
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
                ", elements=" + elements +
                ", participantSet=" + participantSet +
                ", user=" + user +
                '}';
    }
}
