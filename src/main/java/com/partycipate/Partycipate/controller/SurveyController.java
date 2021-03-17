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
@CrossOrigin(origins = "*")
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


    @CrossOrigin(origins = "*")
    @PostMapping("")
    public int addSurvey(@RequestBody SendSurvey survey){
        return surveyService.addSurvey(survey).getId();
    }

    
    //getAll
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

    //getById
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable("id") int id){
        HttpHeaders responesHeaders = new HttpHeaders();
        responesHeaders.set("Access-Control-Allow-Origin", "*");
        return ResponseEntity.ok()
                .headers(responesHeaders)
                .body(surveyService.getSurvey(id));
        //return new ResponseEntity<>(surveyService.getSurvey(id), HttpStatus.OK);
        //TODO fix
    }



    //deleteById
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public int deleteSurveybyId(@PathVariable("id") int id){

        surveyService.deleteSurveybyId(id);
        return id;
    }

    //addSurveyElement
    @CrossOrigin(origins = "*")
    @PostMapping("/element")
    public int addSurveyElement(@RequestBody SendElement sendElement){
       return 0;
    }


}
