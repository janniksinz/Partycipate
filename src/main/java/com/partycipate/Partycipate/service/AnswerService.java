package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.Result;
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
    public Set<Answer> getAnswersByElementId(int id){
        Set<Answer> answers = answerRepository.getAnswersByElementId(id);
        return answers;
    }

    public Result results(Set<Answer> answerSet){
        //calculate all the answers to one Result to send back to the Frontend
        return new Result();
    }

    
}
