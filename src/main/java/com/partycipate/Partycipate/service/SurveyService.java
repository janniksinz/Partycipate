package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.model.SurveyRepositoryGio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepositoryGio surveyRepository;

    public static Survey createSurvey(String title){
        Survey survey = new Survey.Builder().title(title).build();
        //insert the data in the db with methods from the db_service
        return survey;
    }


    public Survey getSurvey(int id){
        //get survey from db with id
        //obviously id and name need to be inserted from db.
        // data needs to be fetched with the db_service
        // like Survey.Builder().id(data.id).name(data.name).build();

        Survey survey = surveyRepository.findSurveyById(id);

        return survey;
    }

    public static Survey getRandomSurvey(int id) {
        //ToDo create content for the dummy survey
        return new Survey.Builder().id(0).creation_date("2021-02-28T18:25:43.511Z").cookie("11111222222333333").build();
    }
}
