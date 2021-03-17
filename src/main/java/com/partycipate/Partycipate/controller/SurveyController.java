package com.partycipate.Partycipate.controller;


import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.dto.SendSurvey;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    @Autowired
    private final SurveyElementService surveyElementService;
    @Autowired
    private final SurveyService surveyService;
    @Autowired
    public SurveyController(SurveyService surveyService, SurveyElementService surveyElementService){
        this.surveyService=surveyService;
        this.surveyElementService = surveyElementService;
    }


    @PostMapping("")
    public int addSurvey(@RequestBody SendSurvey survey){
        int id = surveyService.addSurvey(survey).getId();
        System.out.println("id: " + id);
        return id;
    }

    
    //getAll
    @GetMapping("")
    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

    //getById
    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Survey getSurvey(@PathVariable("id") int id){
        return surveyService.getSurvey(id);
    }



    //deleteById
    @DeleteMapping("/{id}")
    public int deleteSurveybyId(@PathVariable("id") int id){

        surveyService.deleteSurveybyId(id);
        return id;
    }

    //addSurveyElement
    @PostMapping("/element")
    public int addSurveyElement(@RequestBody SendElement sendElement){
       return 0;
    }

}
