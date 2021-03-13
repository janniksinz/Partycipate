package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.service.SurveyService;
import com.partycipate.Partycipate.model.Survey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/survey")
public class SurveyController {
    /*
    *  This is the endpoint for surveys
    *
    *  - get all surveys (GET/survey)
    *  - get survey by id (GET/survey/{id})
    *  - create survey (POST/survey/{id})
    *  - delete Survey (DELETE/survey/{id})
    *  - delete all (DELETE/survey)
    *
    */

    @GetMapping()
    public Set<Object> getAllSurveys(){
        Set<Object> surveys = new HashSet<>();
        surveys.add(new Survey.Builder().build());
        surveys.add(new Survey.Builder().build());
        return surveys;
    }

    @GetMapping("/survey/{id}")
    //@Header  Cookie, Auth_token
    //@Body
    public Survey getSurvey(@PathVariable("id") int id){
        return SurveyService.getRandomSurvey(id);
    }
    /*
    @Get("/{auth_token}")
    public ArrayList<Survey> getAllSurveys(Auth auth_token){
        ////SurveyService.getAllSurveys();
        return new ArrayList<Survey>(); // {surveys[survey, surveyElement[]]}
    }*/

    @GetMapping("/survey/create")
    public Survey createSurvey(/*Auth auth_token,*/ String title, String[] elements){
        //create survey with service here
        // return SurveyService.createSurvey(name);
        //UserService.getUserByAuth(auth_token);
        Survey survey = SurveyService.createSurvey(title);
        //return {survey, surveyElements[]}

        return survey;
    }

    @GetMapping("/survey/update/{id}")
    public Survey updateSurvey(@PathVariable("id") int id){
        Survey a = new Survey.Builder().build();
        return a;
    }

}
