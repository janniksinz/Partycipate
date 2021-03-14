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
public class SurveyService implements ISurveyService{

    private final SurveyDao surveyDao;
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(@Qualifier("fakeDao") SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }

    public Survey addSurvey(Survey survey){
        return surveyDao.insertSurvey(survey);
    }

    @GetMapping()
    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }


    public static Survey getRandomSurvey(int id) {
        //ToDo create content for the dummy survey
        return new Survey.Builder().id(0).creation_date("2021-02-28T18:25:43.511Z").cookie("11111222222333333").title("some Survey").build();
    }


    @Override
    public List<Survey> findAll() {
        return null;
    }

    @Override
    public Optional<Survey> getSurvey(Long id) {
        return Optional.empty();
    }
}
