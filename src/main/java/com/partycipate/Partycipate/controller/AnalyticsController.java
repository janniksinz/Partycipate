package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.TimeLine;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.service.AnalyticsService;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private static final Logger log = LoggerFactory.getLogger(AnalyticsController.class);

    @Autowired
    private SurveyElementService surveyElementService;

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    @GetMapping("/answers/{survey_id}")
    public @ResponseBody ResponseEntity<?> getBasicResults(@PathVariable("survey_id") int survey_id){
        return new ResponseEntity<>(answerService.getBasicResultsForSurvey(survey_id), HttpStatus.OK);
    }

    @PostMapping("/timeline/{survey_id}")
    public @ResponseBody
    ResponseEntity<?> getTimeResults(@PathVariable("survey_id") int survey_id,
                                  @RequestBody TimeLine timeLine){
        return answerService.timeResultsForSurvey(survey_id, timeLine);

    }

    //total number of Partycipants
    @PostMapping("/participants")
    public ResponseEntity<?> getCountParticipants(@RequestBody TimeLine timeline){
        User user = userService.getUserByJWT();
        return new ResponseEntity<>(answerService.getAnswerCountAllSurveys(timeline, user), HttpStatus.OK);
    }
}
