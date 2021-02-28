package com.partycipate.controllers;

import com.partycipate.models.Survey;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;


import com.partycipate.services.SurveyService;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/survey")
public class SurveyController {

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@Header
    //@Body
    public Survey getSurvey(int id){
        return SurveyService.getSurvey(id);
    }

    @Get("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Survey createSurvey(String name){
        //create survey with service here
        return SurveyService.createSurvey(name);
    }

}
