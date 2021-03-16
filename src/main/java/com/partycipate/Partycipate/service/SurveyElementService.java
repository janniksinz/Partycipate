package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.model.SurveyElement;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyElementService {

    @Autowired
    private SurveyElementRepository surveyElementRepository;
    @Autowired
    private SurveyService surveyService;

    @Autowired
    public SurveyElementService(SurveyElementRepository surveyElementRepository) {
        this.surveyElementRepository = surveyElementRepository;
    }
    public int addSurveyElement(SendElement sE){
        SurveyElement surveyElement = new SurveyElement.Builder().may_skip(sE.isMay_skip()).position(sE.getPosition()).question(sE.getQuestion()).type(sE.getType()).build();
        surveyElement.setSurvey(surveyService.getSurvey(sE.getSurvey_id()));
        //ToDo create manual implementation of save to only save surveyElement and not the Survey, too.
        return surveyElementRepository.save(surveyElement).getId();
    }

    /*public int getSurveyIdByElementId(int id){
        return surveyElementRepository.getSurveyIdByElementId(id);
    }*/

    
}
