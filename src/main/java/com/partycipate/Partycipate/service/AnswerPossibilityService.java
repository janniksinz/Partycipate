package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dao.SurveyDao;
import com.partycipate.Partycipate.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AnswerPossibilityService {

    private final SurveyDao surveyDao;
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    public AnswerPossibilityService(@Qualifier("fakeDao") SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }

    
}
