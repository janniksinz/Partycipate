package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.repository.SurveyRepository;
import com.partycipate.Partycipate.service.SurveyService;
import com.partycipate.Partycipate.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

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
    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

}
