package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.RegionUser;
import com.partycipate.Partycipate.repository.Survey_ParticipantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Tuple;
import java.util.*;

@Service
public class AnalyticsService {
    private static final Logger log = LoggerFactory.getLogger(AnalyticsService.class);

    private Survey_ParticipantRepository survey_participantRepository;

    @Autowired
    private AnalyticsService (Survey_ParticipantRepository survey_participantRepository) {
        this.survey_participantRepository = survey_participantRepository;
    }

    public List<RegionUser> getRegionCountForSurvey(int survey_id){
        List<RegionUser> regionUserList = survey_participantRepository.getParticipantCountPerRegion(survey_id);
        return regionUserList;
    }


    /*public MultipleChoiceAnswer getMultipleChoiceAnswer(){
        return new MultipleChoiceAnswer.Builder().values(new int[]{4,4}).build();
    }*/
}
