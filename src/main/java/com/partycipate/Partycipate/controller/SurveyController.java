package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.service.ISurveyService;
import com.partycipate.Partycipate.service.SurveyService;
import com.partycipate.Partycipate.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/survey")
public class SurveyController {
    @Autowired
    private ISurveyService surveyService;
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
    public ModelAndView getAllSurveys(){
        var surveys = (List<Survey>) surveyService.findAll();
        var params = new HashMap<String,Object>();
        params.put("surveys", surveys);
        return new ModelAndView("All Surveys", params);

        /*Set<Object> surveys = new HashSet<>();
        surveys.add(new Survey.Builder().build());
        surveys.add(new Survey.Builder().build());*/

    }

    @GetMapping("/{id}")
    //@Header  Cookie, Auth_token
    //@Body
    public Set<Object> getSurvey(@PathVariable("id") int id){
        Set<Object> surveys = new HashSet<>();
        surveys.add(new Survey.Builder().build());
        surveys.add(new Survey.Builder().build());
        return surveys;
    }
/*
    @Get("/{auth_token}")
    public ArrayList<Survey> getAllSurveys(Auth auth_token){
        ////SurveyService.getAllSurveys();
        return new ArrayList<Survey>(); // {surveys[survey, surveyElement[]]}
    }

    @GetMapping("/survey/create")
    public Survey createSurvey(String title, String[] elements){
        //create survey with service here
        // return SurveyService.createSurvey(name);
        //UserService.getUserByAuth(auth_token);
        Survey survey = SurveyService.createSurvey(title);
        //return {survey, surveyElements[]}

        return survey;
    }

    @PostMapping("/update/{id}")
    public Survey updateSurvey(@PathVariable("id") int id){
        Survey a = new Survey.Builder().build();
        return a;
    }*/

}
