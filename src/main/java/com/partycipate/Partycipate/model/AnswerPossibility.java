package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AnswerPossibility {
    @Id
    @SequenceGenerator(
            name = "answerPossibility_sequence",
            sequenceName = "answerPossibility_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answerPossibility_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;
    private int position;
    private String answer;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "surveyElement_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private SurveyElement surveyElement;

    private AnswerPossibility(Builder builder) {
        this.id = builder.id;

        this.answer = builder.answer;
    }
    @OneToMany(mappedBy = "answerPossibility",cascade = CascadeType.ALL)
    Set<MCAnswerContent> mcAnswerContent;

    public AnswerPossibility(String answer, int position) {
        this.answer=answer;
        this.position=position;
    }

    public static class Builder {
        private int id ;
        private int position = 0;

        private String answer = "";

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder position(int position) {
            this.position = position;
            return this;
        }

        public Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public AnswerPossibility build() {
            return new AnswerPossibility(this);
        }
    }

    @Override
    public String toString() {
        return "AnswerPossibility{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", surveyElement=" + surveyElement +
                '}';
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public SurveyElement getSurveyElement() {
        return surveyElement;
    }

    public void setSurveyElement(SurveyElement surveyElement) {
        this.surveyElement = surveyElement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }


    public Set<MCAnswerContent> getMcAnswerContent() {
        return mcAnswerContent;
    }

    public void setMcAnswerContent(Set<MCAnswerContent> mcAnswerContent) {
        this.mcAnswerContent = mcAnswerContent;
    }
}