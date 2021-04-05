package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.Result;
import com.partycipate.Partycipate.dto.SendAnswer;
import com.partycipate.Partycipate.model.Participant;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.ParticipantService;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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
    @GetMapping("/{id}")
    public Optional <Participant> getParticipant(@PathVariable ("id")int participant_id){
       Optional <Participant> p1= participantService.getParticipant(participant_id);
       System.out.println(p1);
       return p1;
    }

    //getBasicResultsForWholeSurvey
    //{},{},{}

    //getBasicResults
    @GetMapping("/results/{survey_id}")
    @CrossOrigin(origins = "*")
    public Set<Result> getBasicResults(@PathVariable ("survey_id")int survey_id){
        return answerService.getAllResultsForSurvey(survey_id);
    }

    //sendAnswer
    @PostMapping("/answer")
    @CrossOrigin(origins = "*")
    public int addAnswer(@RequestBody SendAnswer sendAnswer){
        return participantService.addAnswer(sendAnswer).getId();
    }


}
