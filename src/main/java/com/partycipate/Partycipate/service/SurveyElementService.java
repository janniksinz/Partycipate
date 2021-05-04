package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.SurveyElement;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SurveyElementService {
    private static final Logger log = LoggerFactory.getLogger(SurveyElementService.class);

    @Autowired
    private SurveyElementRepository surveyElementRepository;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyElementService surveyElementService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerPossibilityService answerPossibilityService;


    public Set<SurveyElement> getSurveyElementSetBySurveyID(int survey_id){
       return surveyElementRepository.findAllBySurveyId(survey_id);
    }

}
