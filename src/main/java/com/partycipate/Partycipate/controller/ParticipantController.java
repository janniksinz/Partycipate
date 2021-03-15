package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.Result;
import com.partycipate.Partycipate.model.SendAnswer;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/participant")

public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private AnswerService answerService;

    //getSurveyById
    @GetMapping("/{id}")
    public String getSurvey(int id){

        return "";
    }

    //getBasicResults
    @GetMapping("/results")
    public Result getBasicResults(){
        return answerService.results();
    }

    //sendAnswer
    @PostMapping("/answer")
    public void addAnswer(@RequestBody Answer answer){
        participantService.addAnswer(answer);
    }


}
