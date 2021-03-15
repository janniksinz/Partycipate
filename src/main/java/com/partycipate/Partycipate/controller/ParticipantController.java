package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.dto.Result;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.ParticipantService;
import com.partycipate.Partycipate.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AnswerService answerService;

    //getSurveyById
    @GetMapping("/{id}")
    public Survey getSurvey(int survey_id){
        return surveyService.getSurveyBySurveyId(survey_id);
    }

    //getBasicResultsForWholeSurvey
    //{},{},{}

    //getBasicResults
    @GetMapping("/results/{element_id}")
    public Result getBasicResults(@PathVariable ("element_id")int element_id){
        //ToDo fix internal Server error
        System.out.println(answerService.results(element_id));
        return answerService.results(element_id);
    }

    //sendAnswer
    @PostMapping("/answer")
    public void addAnswer(@RequestBody Answer answer){
        participantService.addAnswer(answer);
    }


}
