package com.partycipate.Partycipate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("/partycipate")
public class ParticipantController {

    @GetMapping("/{id}")
    public String getSurvey(int id){

        return "";
    }

    @GetMapping("/results/{id}")
    public String getResults(@PathVariable("id") int id){
        return "";
    }

}
