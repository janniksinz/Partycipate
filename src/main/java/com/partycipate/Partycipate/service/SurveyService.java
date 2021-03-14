package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey addSurvey(Survey survey){
        return surveyRepository.save(survey);
    }

    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }

    //getSurveyByUserId
    public @ResponseBody
    Set<Survey> getSurveyByUserId(int user_id){
        return surveyRepository.getSurveyByUser(user_id);
    }
    /*kann Optional keine oder viele zurückgeben???*/







    public static Survey getRandomSurvey(int id) {
        //ToDo create content for the dummy survey
        return new Survey.Builder().id(0).creation_date("2021-02-28T18:25:43.511Z").cookie("11111222222333333").title("some Survey").build();
    }



    public List<Survey> findAll() {
        return null;
    }


    public Survey getSurvey(int id) {
        return surveyRepository.findById(id);
    }
    /*public Iterable<Survey> getUserSurveys(int id){
        return (Iterable<Survey>) surveyRepository.findAllByUser_id(id);
    }*/
}
