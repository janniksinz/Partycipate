package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.Result;
import com.partycipate.Partycipate.dto.SendAnswer;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.ParticipantService;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private SurveyElementService surveyElementService;

    @Autowired
    private SurveyElementRepository surveyElementRepository;

    //getSurveyById
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public Survey getSurvey(int survey_id){
        return surveyService.getSurveyBySurveyId(survey_id);
    }

    //getBasicResultsForWholeSurvey
    //{},{},{}

    //getBasicResults
    @CrossOrigin(origins = "*")
    @GetMapping("/results/{survey_id}")
    public Set<Result> getBasicResults(@PathVariable ("survey_id")int survey_id){
        return answerService.getAllResultsForSurvey(survey_id);
    }

    //sendAnswer
    @CrossOrigin(origins = "*")
    @PostMapping("/answer")
    public int addAnswer(@RequestBody SendAnswer sendAnswer){
        return participantService.addAnswer(sendAnswer).getId();
    }


}
