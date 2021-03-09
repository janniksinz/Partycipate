package com.partycipate.controllers;

import com.partycipate.models.Survey;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;


import com.partycipate.services.SurveyService;

import java.util.ArrayList;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/survey")
public class SurveyController {

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@Header  Cookie, Auth_token
    //@Body
    public Survey getSurvey(/*Auth auth_token,*/ int id){
        // {Survey}
        return SurveyService.getSurvey(id);
    }
    /*
    @Get("/{auth_token}")
    public ArrayList<Survey> getAllSurveys(Auth auth_token){
        ////SurveyService.getAllSurveys();
        return new ArrayList<Survey>(); // {surveys[survey, surveyElement[]]}
    }*/

    @Get("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Survey createSurvey(/*Auth auth_token,*/ String title, String[] elements){
        //create survey with service here
        // return SurveyService.createSurvey(name);
        //UserService.getUserByAuth(auth_token);
        Survey survey = SurveyService.createSurvey(title);
        //return {survey, surveyElements[]}

        return survey;
    }

    @Get
    public Survey updateSurvey(){
        Survey a = new Survey.Builder().build();
        return a;
    }

}
