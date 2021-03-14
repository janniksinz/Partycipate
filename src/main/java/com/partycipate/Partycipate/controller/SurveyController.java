package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.service.ISurveyService;
import com.partycipate.Partycipate.service.SurveyService;
import com.partycipate.Partycipate.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService){
        this.surveyService=surveyService;
    }

    @PostMapping("")
    public void addSurvey(@RequestBody Survey survey){
        surveyService.addSurvey(survey);
    }

    @GetMapping("")
    public List<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

}
