package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.ResultMc;
import com.partycipate.Partycipate.dto.TimeLine;
import com.partycipate.Partycipate.dto.TimeResultMc;
import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.AnswerPossibility;
import com.partycipate.Partycipate.model.MCAnswerContent;
import com.partycipate.Partycipate.model.SurveyElement;
import com.partycipate.Partycipate.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerPossibilityService answerPossibilityService;

    @Autowired
    private McAnswerContentService mcAnswerContentService;

    @Autowired
    private SurveyElementService surveyElementService;


    @Autowired
    public AnswerService(AnswerRepository answerRepository, McAnswerContentService mcAnswerContentService, SurveyElementService surveyElementService) {
        this.answerRepository=answerRepository;
        this.mcAnswerContentService=mcAnswerContentService;
        this.surveyElementService=surveyElementService;
    }

//    getBasicResults
    public Set<ResultMc> getBasicResultsForSurvey(int survey_id){
        Iterator<SurveyElement> sEI = surveyElementService.getSurveyElementSetBySurveyID(survey_id).iterator();
        Set<ResultMc> resultMcs = new HashSet<ResultMc>();
//        iterate over SurveyElements to get Results for each
        while(sEI.hasNext()){
            int value = sEI.next().getId();
            // add Results from Element to Set<Results>
            resultMcs.add(results(value));
        }

        return resultMcs;
    }
//    helperMethod - getBasicResults
    public ResultMc results(int element_id){
        //calculate all the answers to one Result to send back to the Frontend
        System.out.println("Start results ");
        int count = answerPossibilityService.getCountOfAnswersPossibilities(element_id);
        System.out.println("count "+ count);
        //brauche Set von Answers; ArrayList initialisen mit Laenge count; Iterator durchgehen und ArrayList hochzaehlen;
        // ArrayList in Result setzen (setResult)
        Set<Answer> answers = answerRepository.getAnswersByElementId(element_id);
        ResultMc resultMc = new ResultMc();
        // count participants
        Optional<Integer> value1 = answerRepository.getCountParticipants(element_id);
        resultMc.setCount_participants(value1.isPresent() ? value1.get() : 0);
        ArrayList<Integer> counting_results = new ArrayList<>(count);
        // initialize arrayList with default values 0
        for (int i =0; i<count;i++){
            counting_results.add(i, 0);
        }


        Iterator<Answer> iterator = answers.iterator();
        // go through every answer for ElementId
        while (iterator.hasNext()){
            Answer a = iterator.next();
            // get all MCAnswerContents for Answer a
            Iterable<MCAnswerContent> mcac =  mcAnswerContentService.getAllMcAnswerContentByAnswerId(a.getId());

            Iterator<MCAnswerContent> mcacIterator = mcac.iterator();
            // go through every MCAnswerContent content

            while(mcacIterator.hasNext()){

                MCAnswerContent content = mcacIterator.next();
                // get AP for MCAnswerContent
                AnswerPossibility answerPossibility = content.getAnswerPossibility();
                // reference the id
                int APid = answerPossibility.getId();
                int position = answerPossibility.getPosition(); //content.getPosition();
                int value = counting_results.get(position-1);

                value += 1;

                counting_results.set(position-1, value);

            }
        }


        resultMc.setResults(counting_results);
        System.out.println("End of method ");
        return resultMc;
        // Anmerkung
    }

    /**
     * getTimeResults for Survey and TimeLine
     * */
    public Set<TimeResultMc> getTimeResultsForSurvey(int survey_id, TimeLine timeLine){
        Iterator<SurveyElement> sEI = surveyElementService.getSurveyElementSetBySurveyID(survey_id).iterator();
        Set<TimeResultMc> timeResultMcs = new HashSet<>();
//        iterate over each Surveyelement to get List of TimeResults
        while (sEI.hasNext()){
            int value = sEI.next().getId();
            timeResultMcs.add(timeResults(value, timeLine));
        }
        return timeResultMcs;
    }

    /**
     * helperMethod - getTimeResultsForSurvey
     * */
    public TimeResultMc timeResults(int element_id, TimeLine timeLine){
//        get all results for the survey_element
//        iterate through every day from start to end
//        filter for results from that day
//        call helpermethod, that aggregates those results to ResultMc
//
//        aggregateMcResults()
        return null;
    }

    public ResultMc aggregateMcResults(Iterator<MCAnswerContent> mcacIter){

        return null;
    }


    /**
     * Trims the Time to 00-00-00
     * */
    public static Date trim(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTime();
    }
}