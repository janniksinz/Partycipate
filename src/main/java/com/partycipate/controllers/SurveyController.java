package com.partycipate.controllers;

import com.partycipate.models.Survey;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import com.partycipate.services.SurveyService;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/survey")
public class SurveyController {

    @Get("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Survey getSurvey(int id){

        return new Survey.Builder().id(id).name("GetSurvey").build();
    }

}
