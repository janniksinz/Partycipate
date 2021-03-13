package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.service.SurveyService;
import com.partycipate.Partycipate.model.Survey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SurveyController {

    @GetMapping("/survey/{id}")
    //@Header  Cookie, Auth_token
    //@Body
    public Survey getSurvey(@PathVariable("id") int id){
        // {Survey}
        return SurveyService.getSurvey(id);
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
