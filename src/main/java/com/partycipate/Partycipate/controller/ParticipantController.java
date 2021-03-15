package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/participant")

public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/{id}")
    public String getSurvey(int id){

        return "";
    }

    @GetMapping("/results/{id}")
    public String getResults(@PathVariable("id") int id){
        return "";
    }

    @PostMapping("/answer")
    public void addAnswer(@RequestBody Answer answer){
        participantService.addAnswer(answer);
    }

}
