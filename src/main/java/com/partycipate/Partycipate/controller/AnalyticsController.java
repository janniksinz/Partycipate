package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.service.AnalyticsService;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.SurveyElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/answers/{element_id}")
    public @ResponseBody Set<Answer> getAnswersByElementId(@PathVariable("element_id") int element_id){
        return answerService.getAnswersByElementId(element_id);
    }


}
