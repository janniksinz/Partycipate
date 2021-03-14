package com.partycipate.Partycipate.dao;

import com.partycipate.Partycipate.model.Survey;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SurveyDao {

    Survey insertSurvey(UUID id, Survey survey);

    default Survey insertSurvey(Survey survey){
        UUID id = UUID.randomUUID();
        return insertSurvey(id, survey);
    }

    List<Survey> selectAllSurveys();


}
