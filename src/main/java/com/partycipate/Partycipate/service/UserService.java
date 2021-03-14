package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dao.SurveyDao;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final SurveyDao surveyDao;
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    public UserService(@Qualifier("fakeDao") SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }


}
