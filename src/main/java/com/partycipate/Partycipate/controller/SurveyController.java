package com.partycipate.Partycipate.controller;


import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.dto.SendSurvey;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/survey")
@CrossOrigin(origins = "*")
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

//    addSurvey
    @PostMapping("")
    public int addSurvey(@RequestBody SendSurvey sendsurvey){
        int id = surveyService.addSurvey(sendsurvey).getId(); //TODO getId() throws "(InvocationTargetException ex)" addSurvey POST doesnt work
        System.out.println("id: " + id);
        return id;
    }

//    getAll
    @GetMapping("")
    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyService.getAllSurveys();
    }

//    getById
    @GetMapping("/{id}")
    public Survey getSurvey(@PathVariable("id") int id){
        return surveyService.getSurveyBySurveyId(id);
    }

//    deleteById
    @DeleteMapping("/{id}")
    public int deleteSurveybyId(@PathVariable("id") int id){

        surveyService.deleteSurveybyId(id);
        return id;
    }

//    addSurveyElement
    @PostMapping("/element")
    public int addSurveyElement(@RequestBody SendElement sendElement){
        return 0;
}

}
