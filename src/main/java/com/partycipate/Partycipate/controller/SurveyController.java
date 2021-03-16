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
        return surveyService.addSurvey(survey);
    }

    //getAll
    @GetMapping("")
    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

    //getById
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable("id") int id){
        HttpHeaders responesHeaders = new HttpHeaders();
        responesHeaders.set("Access-Control-Allow-Origin", "*");
        return ResponseEntity.ok()
                .headers(responesHeaders)
                .body(surveyService.getSurvey(id));
    }



    //deleteById
    @DeleteMapping("/{id}")
    public int deleteSurveybyId(@PathVariable("id") int id){
        //ToDo send Exception in Case of already deleted
        surveyService.deleteSurveybyId(id);
        return id;
    }

    //addSurveyElement
    @PostMapping("/element")
    public int addSurveyElement(@RequestBody SendElement sendElement){
        System.out.println(sendElement.getAnswerPossibilitySet().stream().findFirst());
        System.out.println(sendElement.getAnswerPossibilitySet().stream().count());

        return surveyElementService.addSurveyElement(sendElement);
    }


}
