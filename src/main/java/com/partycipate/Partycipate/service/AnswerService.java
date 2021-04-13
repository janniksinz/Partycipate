package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.ResultMc;
import com.partycipate.Partycipate.dto.TimeLine;
import com.partycipate.Partycipate.dto.TimeResultMc;
import com.partycipate.Partycipate.dto.TimeResultMcList;
import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.AnswerPossibility;
import com.partycipate.Partycipate.model.MCAnswerContent;
import com.partycipate.Partycipate.model.SurveyElement;
import com.partycipate.Partycipate.repository.AnswerRepository;
import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnswerService {
    private static final Logger log = LoggerFactory.getLogger(AnswerService.class);

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

    /**
    * getBasicResults
     * <author> Jannik Sinz - jannik.sinz@ibm.com </author>
    * */
    public Set<ResultMc> getBasicResultsForSurvey(int survey_id){
        Iterator<SurveyElement> sEI = surveyElementService.getSurveyElementSetBySurveyID(survey_id).iterator();
        Set<ResultMc> resultMcs = new HashSet<ResultMc>();
//        iterate over SurveyElements to get Results for each
        while(sEI.hasNext()){
            int element_id = sEI.next().getId();
//            iterate over Element
            // add Results from Element to Set<Results>
            Set<Answer> answers = answerRepository.getAnswersByElementId(element_id);
            Iterator<Answer> answerIterator= answers.iterator();

            resultMcs.add(aggregateMcResults(answerIterator,element_id));
        }

        return resultMcs;
    }

    /**
     * !!!depricated!!! helperMethod - getBasicResults !!!depricated!!!
     * should be replaced by aggregate for McAnswer
     * <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     * */




/*   //DEPRECATED
    public ResultMc results(int element_id){
        //calculate all the answers to one Result to send back to the Frontend
        System.out.println("Start results ");
        int count = answerPossibilityService.getCountOfAnswersPossibilities(element_id);
        System.out.println("count "+ count);
        //brauche Set von Answers; ArrayList initialisen mit Laenge count; Iterator durchgehen und ArrayList hochzaehlen;
        // ArrayList in Result setzen (setResult)
        Set<Answer> answers = answerRepository.getAnswersByElementId(element_id);
        ResultMc resultMc = new ResultMc();
        resultMc.setElement_id(element_id);
        // count participants
        Optional<Integer> countParticipants = answerRepository.getCountParticipants(element_id);
        resultMc.setCount_participants(countParticipants.orElse(0));
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
                int position = answerPossibility.getPosition(); //content.getPosition();
                int value = counting_results.get(position-1);

                value += 1;

                counting_results.set(position-1, value);

            }
        }


        resultMc.setResults(counting_results);
        System.out.println("End of method ");
        resultMc.setElement_id(element_id);
        return resultMc;
        // Anmerkung
    }/*

    /**
     * getTimeResults for Survey and TimeLine
     * <authors>
     *      <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     *      <author> Giovanni Carlucci </author>
     * </authors>
     * */
    public ResponseEntity<?> timeResultsForSurvey(int survey_id, TimeLine timeLine){

        log.info("TimeResults: Retrieving timeResults for Survey Id: {} between {} and {}", survey_id, trim(timeLine.getStart()), trim(timeLine.getEnd()));
        Date start = trim(timeLine.getStart());
        Date end = trim(timeLine.getEnd());
        if(trim(timeLine.getStart()).compareTo(trim(timeLine.getEnd())) <= 0 ){
            Date now = trim(new Date(System.currentTimeMillis()));
            if (start.compareTo(now)<= 0 && end.compareTo(now) <= 0){
                Iterator<SurveyElement> sEI = surveyElementService.getSurveyElementSetBySurveyID(survey_id).iterator();

//        iterate over each Surveyelement to get List of TimeResults
                Set<TimeResultMcList> list = new HashSet<>();
                log.info("TimeResults: Survey has Elements: {}", sEI.hasNext());
                while (sEI.hasNext()){
                    int element_id = sEI.next().getId();
//            get TimeResultMc for Survey_Element
                    log.info("TimeResults: Getting answers for Survey_Element: {}", element_id);
                    list.add(new TimeResultMcList(timeResultsForElement(element_id, timeLine), element_id));
                }
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(new ResponseMessage("Start or/and End date are in the future"), HttpStatus.BAD_REQUEST);
            }

        }
        else{
                return new ResponseEntity<>(new ResponseMessage("Start date is after end date"), HttpStatus.BAD_REQUEST);
        }

    }



    /**
     * helperMethod - getTimeResultsForSurvey
     * <authors>
     *      <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     *      <author> Giovanni Carlucci </author>
     * </authors>
     * */
    public Set<TimeResultMc> timeResultsForElement(int element_id, TimeLine timeLine){
//        get all answers for the survey_element
        Set<Answer> answerSet = answerRepository.getAnswersByElementId(element_id);
        Iterator<Answer> answerSetIt= answerSet.iterator();
        if (answerSetIt.hasNext()){
            //save every day in Set<ResultMc>
            Set<TimeResultMc> timeResultMcSet = new HashSet<>();
            Date start = trim(timeLine.getStart());
            Date end = trim(timeLine.getEnd());
            Date today = start;
//        iterate over Date until start is after end - start++
//        and create TimeResultMc
            while (today.compareTo(end) <= 0){
                log.info("TimeResult: Getting answers for day {}", today);
                //            iterate over answers and filter for current date

                Iterator<Answer> todayAnswers = filterByDate(answerSet,today);
                //            call helpermethod, that aggregates those results to ResultMc
                //            get ResultMc for the Day
                if(todayAnswers.hasNext()) {
                    ResultMc resultMc = aggregateMcResults(todayAnswers, element_id);
                    TimeResultMc timeResultMc = new TimeResultMc(today, resultMc);
                    timeResultMcSet.add(timeResultMc);
                    //            make into TimeResultMc - return
                }
                else{
                    ResultMc resultMc = aggregateMcResults(todayAnswers, element_id);
                    TimeResultMc timeResultMc = new TimeResultMc(today, resultMc);
                    timeResultMcSet.add(timeResultMc);
                }
                Calendar c = Calendar.getInstance();
                c.setTime(today);
                c.add(Calendar.DATE, 1);
                today = c.getTime();

            }

            return timeResultMcSet;
        }
       else {
           return null;
        }


    }

    public  Iterator<Answer> filterByDate (Set<Answer> answerSet, Date today){
       return answerSet.stream().filter(a -> trim(a.getDate()).equals(today)).iterator();
    }
    /**
     * helper method - aggregates Answers for MC
     * <authors>
     *      <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     *      <author> Giovanni Carlucci </author>
     * </authors>
     * */
    public ResultMc aggregateMcResults(Iterator<Answer> answers, int element_id){
        ResultMc resultMc = new ResultMc();
        Optional<Integer> countParticipants = answerRepository.getCountParticipants(element_id);
        resultMc.setCount_participants(countParticipants.orElse(0));
//        initialize an empty List with the length for every AnswerPossibility
        int count = answerPossibilityService.getCountOfAnswersPossibilities(element_id);
        ArrayList<Integer> counting_results = new ArrayList(count);
        for (int i = 0; i<count; i++){
            counting_results.add(i ,0);
        }

//        iterate over valid answers for the day
        while (answers.hasNext()){
            Answer a = answers.next();
            log.info("Results: Iterating over AnswersId {}", a.getId());
            Iterator<MCAnswerContent> mcacIter = mcAnswerContentService.getAllMcAnswerContentByAnswerId(a.getId()).iterator();
//            iterate over answerContents and add results to counting_results
            while (mcacIter.hasNext()){
                MCAnswerContent content = mcacIter.next();
                AnswerPossibility answerPossibility = content.getAnswerPossibility();
//                count answer in counting_results
                int position = answerPossibility.getPosition();
                int value = counting_results.get(position-1);
                value+=1;
                counting_results.set(position-1, value);
            }
        }
        resultMc.setResults(counting_results);
        resultMc.setElement_id(element_id);
        return resultMc;
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
        calendar.set(Calendar.HOUR_OF_DAY, 2);

        return calendar.getTime();
    }
}