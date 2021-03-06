package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SurveyElement {
    @Id
    @SequenceGenerator(
            name = "surveyElement_sequence",
            sequenceName = "surveyElement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "surveyElement_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    private int position;
    private String type;
    private String question;
    private boolean may_skip;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="survey_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Survey survey;

    @OneToMany(mappedBy = "surveyElement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerPossibility> answer_possibilities = new ArrayList<>();

    @OneToMany(mappedBy = "surveyElement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Answer> answers = new ArrayList<>();

    private SurveyElement(Builder builder){
        this.id = builder.id;
        this.position= builder.position;
        this.type= builder.type;
        this.question= builder.question;
        this.may_skip= builder.may_skip;
    }

    public SurveyElement() {}

    //custom constructor for optional parameters
    public static class Builder{

        private int id ;

        private int position = 0;
        private String type = "";
        private String question = "";
        private boolean may_skip = true;

        public Builder id(int id){
            this.id=id;
            return this;
        }


        public Builder position(int position){
            this.position=position;
            return this;
        }
        public Builder type(String type){
            this.type=type;
            return this;
        }
        public Builder question(String question){
            this.question=question;
            return this;
        }
        public Builder may_skip(boolean may_skip){
            this.may_skip=may_skip;
            return this;
        }
        public SurveyElement build(){
            return new SurveyElement(this);
        }
    }

    public List<AnswerPossibility> getAnswer_possibilities() {
        return answer_possibilities;
    }

    public void setAnswer_possibilities(List<AnswerPossibility> answerPossibilities) {
        this.answer_possibilities = answerPossibilities;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isMay_skip() {
        return may_skip;
    }

    public void setMay_skip(boolean may_skip) {
        this.may_skip = may_skip;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "SurveyElement{" +
                "id=" + id +
                ", position=" + position +
                ", type='" + type + '\'' +
                ", question='" + question + '\'' +
                ", may_skip=" + may_skip +
                ", survey=" + survey +
                ", answerPossibilities=" + answer_possibilities +
                ", answers=" + answers +
                '}';
    }
}
