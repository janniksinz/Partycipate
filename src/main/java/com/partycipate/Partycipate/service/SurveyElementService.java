package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.Result;
import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.model.SurveyElement;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class SurveyElementService {

    @Autowired
    private SurveyElementRepository surveyElementRepository;
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyElementService surveyElementService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    public SurveyElementService(SurveyElementRepository surveyElementRepository) {
        this.surveyElementRepository = surveyElementRepository;
    }
    public int addSurveyElement(SendElement sE){
        SurveyElement surveyElement = new SurveyElement.Builder().may_skip(sE.isMay_skip()).position(sE.getPosition()).question(sE.getQuestion()).type(sE.getType()).build();
        surveyElement.setSurvey(surveyService.getSurvey(sE.getSurvey_id()));

        surveyElementRepository.saveSurveyElement(surveyElementRepository.getLastId()+1,surveyElement.isMay_skip(),surveyElement.getPosition(),surveyElement.getQuestion(),surveyElement.getType(), sE.getSurvey_id());
        return surveyElement.getId();
    }
    public Set<SurveyElement> getSurveyElementSetBySurveyID(int survey_id){
       return surveyElementRepository.findAllBySurveyId(survey_id);
    }
    /*public int getSurveyIdByElementId(int id){
        return surveyElementRepository.getSurveyIdByElementId(id);
    }*/


    
}
