package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    public AnswerService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    
}
