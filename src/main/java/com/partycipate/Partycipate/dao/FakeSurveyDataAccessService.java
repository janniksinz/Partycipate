package com.partycipate.Partycipate.dao;

import com.partycipate.Partycipate.model.Survey;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakeSurveyDataAccessService implements SurveyDao{

    private static List<Survey> DB = new ArrayList<>();

    @Override
    public Survey insertSurvey(UUID id, Survey survey) {
        DB.add(new Survey.Builder().build());
        return survey;
    }
}
