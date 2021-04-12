package com.partycipate.Partycipate.controller;


import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.dto.SendSurvey;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/survey")
@CrossOrigin(origins = "*")
public class SurveyController {
    private static final Logger log = LoggerFactory.getLogger(SurveyController.class);

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
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> addSurvey(@RequestBody SendSurvey sendsurvey){
        log.info("addSurvey: Inserting edSurvey for user {}", sendsurvey.getUser_id());
        int id = surveyService.addSurvey(sendsurvey).getId();
        log.info("addSurvey: Inserted Survey with Id: {}", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
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
    public ResponseEntity<?> deleteSurveybyId(@PathVariable("id") int id) throws EmptyResultDataAccessException {
        log.info("deleteSurvey: Deleting Survey {}", id);
        return new ResponseEntity<>(surveyService.deleteSurveybyId(id), HttpStatus.OK);
    }

//    addSurveyElement
    @PostMapping("/element")
    public int addSurveyElement(@RequestBody SendElement sendElement){
        return 0;
}

}
