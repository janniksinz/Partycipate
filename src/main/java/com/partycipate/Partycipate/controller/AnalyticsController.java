package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.TimeLine;
import com.partycipate.Partycipate.dto.TimeResultMc;
import com.partycipate.Partycipate.dto.TimeResultMcList;
import com.partycipate.Partycipate.service.AnalyticsService;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.SurveyElementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private static final Logger log = LoggerFactory.getLogger(AnalyticsController.class);

    @Autowired
    SurveyElementService surveyElementService;

    @Autowired
    AnalyticsService analyticsService;

    @Autowired
    AnswerService answerService;

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


}
