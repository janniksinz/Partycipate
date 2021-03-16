package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendSurvey;
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
import java.util.logging.Logger;


@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public int addSurvey(SendSurvey surveyS){
        Survey survey = new Survey.Builder().creation_date(surveyS.getCreation_date()).title(surveyS.getTitle()).build();
        survey.setUser(userService.getUser(surveyS.getUser_id()));
        return surveyRepository.save(survey).getId();
    }

    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }

    //getSurveyBySurveyId
    public Survey getSurveyBySurveyId(int survey_id){ return surveyRepository.findById(survey_id); }



    public void deleteSurveybyId(int id)  {
        try {
            surveyRepository.deleteById(id);
        }
        catch (Exception e){
            System.err.println("Id not found");
            e.printStackTrace();
        }
    }




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
