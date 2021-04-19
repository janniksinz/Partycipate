package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.SendAnswer;
import com.partycipate.Partycipate.model.Participant;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.ParticipantService;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {
    private static final Logger log = LoggerFactory.getLogger(ParticipantController.class);

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
    public Participant getParticipant(@PathVariable ("id")int participant_id){
       return participantService.getParticipant(participant_id).get();
    }

    //getBasicResultsForWholeSurvey
    //{},{},{}

    //getBasicResults
    @GetMapping("/results/{survey_id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getBasicResults(@PathVariable ("survey_id") int survey_id){
        return new ResponseEntity<>(answerService.getBasicResultsForSurvey(survey_id), HttpStatus.OK);
    }

    //sendAnswer
    @PostMapping("/answer")
    @CrossOrigin(origins = "*")
    public int addAnswer(@RequestBody SendAnswer sendAnswer){
        return participantService.addAnswer(sendAnswer).getId();
    }


}
