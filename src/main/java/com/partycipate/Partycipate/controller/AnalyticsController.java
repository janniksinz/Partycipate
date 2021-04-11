package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.ResultMc;
import com.partycipate.Partycipate.dto.TimeLine;
import com.partycipate.Partycipate.dto.TimeResultMc;
import com.partycipate.Partycipate.service.AnalyticsService;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.SurveyElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    SurveyElementService surveyElementService;

    @Autowired
    AnalyticsService analyticsService;

    @Autowired
    AnswerService answerService;

    @GetMapping("/answers/{survey_id}")
    public @ResponseBody Set<ResultMc> getBasicResults(@PathVariable("survey_id") int survey_id){
        return answerService.getBasicResultsForSurvey(survey_id);
    }

    @GetMapping("/timeline/{survey_id}")
    public @ResponseBody Set<TimeResultMc> getTimeResults(@PathVariable("survey_id") int survey_id,
                                                          @RequestBody TimeLine timeLine){
        return answerService.getTimeResultsForSurvey(survey_id, timeLine);
    }


}
