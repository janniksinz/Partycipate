package com.partycipate.Partycipate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
public class RangeAnswerPossibility {
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
    private int answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "surveyElement_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private SurveyElement surveyElement;

    @OneToMany(mappedBy = "answerPossibility",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<RangeAnswerContent> rangeAnswerContents;

    public RangeAnswerPossibility() {}
    private RangeAnswerPossibility(Builder builder) {
        this.id = builder.id;
        this.answer = builder.answer;
        this.position=builder.position;
        this.rangeAnswerContents=builder.rangeAnswerContents;
    }

    public RangeAnswerPossibility(int answer, int position) {
        this.answer=answer;
        this.position=position;
    }

    public static class Builder {
        private int id ;
        private int position = 0;
        private int answer = 0;
        private Set<RangeAnswerContent> rangeAnswerContents = null;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder rangeAnswerContent(Set<RangeAnswerContent> rangeAnswerContents){
            this.rangeAnswerContents=rangeAnswerContents;
            return this;
        }

        public Builder position(int position) {
            this.position = position;
            return this;
        }

        public Builder answer(int answer) {
            this.answer = answer;
            return this;
        }

        public RangeAnswerPossibility build() {
            return new RangeAnswerPossibility(this);
        }
    }

    @Override
    public String toString() {
        return "RangeAnswerPossibility{" +
                "id=" + id +
                ", position=" + position +
                ", answer=" + answer +
                ", surveyElement=" + surveyElement +
                ", rangeAnswerContents=" + rangeAnswerContents +
                '}';
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
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

    public Set<RangeAnswerContent> getRangeAnswerContents() {
        return rangeAnswerContents;
    }

    public void setRangeAnswerContents(Set<RangeAnswerContent> rangeAnswerContents) {
        this.rangeAnswerContents = rangeAnswerContents;
    }
}