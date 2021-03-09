package com.partycipate.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/partycipate")
public class PublicAnalyticsController {

    @Get ("/{id}")
    public String getSurvey(int id){

        return "";
    }
    /*
    @Post("/{id}")
    @Header
    public boolean saveSurvey(){

        return true;
    }*/

    @Get("/results/{id}")
    public String getResults(int id){
        return "";
    }

}
