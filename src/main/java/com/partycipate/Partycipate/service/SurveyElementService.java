package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.repository.SurveyElementRepository;
import com.partycipate.Partycipate.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SurveyElementService {

    @Autowired
    private SurveyElementRepository surveyElementRepository;

    @Autowired
    public SurveyElementService(SurveyElementRepository surveyElementRepository) {
        this.surveyElementRepository = surveyElementRepository;
    }

    /*public int getSurveyIdByElementId(int id){
        return surveyElementRepository.getSurveyIdByElementId(id);
    }*/

    
}
