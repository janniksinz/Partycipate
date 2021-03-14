package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.repository.AnswerRepository;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import com.partycipate.Partycipate.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    //getAnswersByElementId
    public HashMap<Set<Answer>, Integer> getAnswersByElementId(int id){
        Set<Answer> answers = answerRepository.getAnswersByElementId(id);
        //int surveyId = SurveyElementService.getSurveyIdByElementId(id);
        int surveyId = 1;
        HashMap<Set<Answer>, Integer> answer = new HashMap(){};
        answer.put(answers,surveyId);
        return answer;
    }

    
}
