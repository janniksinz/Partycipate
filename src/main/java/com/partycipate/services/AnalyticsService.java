package com.partycipate.services;

import com.partycipate.models.MultipleChoiceAnswer;

public class AnalyticsService {

    public MultipleChoiceAnswer getMultipleChoiceAnswer(){
        return new MultipleChoiceAnswer.Builder().values(new int[]{4,4}).build();
    }
}
