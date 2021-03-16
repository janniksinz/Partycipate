package com.partycipate.Partycipate.controller;


import com.partycipate.Partycipate.dto.SendSurvey;
import com.partycipate.Partycipate.service.SurveyService;
import com.partycipate.Partycipate.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    private final SurveyService surveyService;
    @Autowired
    public SurveyController(SurveyService surveyService){
        this.surveyService=surveyService;
    }

    @PostMapping("")
    public int addSurvey(@RequestBody SendSurvey survey){
        return surveyService.addSurvey(survey);
    }

    //getAll
    @GetMapping("")
    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

    //getById
    @GetMapping("/{id}")
    public @ResponseBody Survey getSurvey(@PathVariable("id") int id){
        return surveyService.getSurvey(id);
    }



    //deleteById
    @DeleteMapping("/{id}")
    public int deleteSurveybyId(@PathVariable("id") int id){
        //ToDo send Exception in Case of already deleted
        surveyService.deleteSurveybyId(id);
        return id;
    }


}
