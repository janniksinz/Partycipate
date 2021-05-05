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

    /**
     * getRegionCountForSurveyBySurvey_id
     * <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de </author>
     * */
    public List<RegionUser> getRegionCountForSurveyBySurvey_id(int survey_id){
        List<RegionUser> regionUserList = survey_participantRepository.getParticipantCountPerRegionBySurvey_id(survey_id);
        return regionUserList;
    }

    /**
     * getRegionCountForSurveyByUser_id
     * <author> Ines Maurer - wi19185@lehre.dhbw-stuttgart.de </author>
     * */
    public List<RegionUser> getRegionCountForSurveyByUser_id(int user_id){
        List<RegionUser> regionUserList = survey_participantRepository.getParticipantCountPerRegionByUser_id(user_id);
        return regionUserList;
    }

    /**
     * getRegionCountForAllSurveys
     * <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de </author>
     * <author> Jarg Heyll - wi19225@lehre.dhbw-stuttgart.de </author>
     * */
    public List<RegionUser> getRegionCountForAllSurveys(){
        List<RegionUser> regionUserList = survey_participantRepository.getTotalParticipantCountPerRegion();
        return regionUserList;
    }

}
