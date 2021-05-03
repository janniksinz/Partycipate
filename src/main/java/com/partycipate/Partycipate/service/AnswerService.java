package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.*;
import com.partycipate.Partycipate.model.*;
import com.partycipate.Partycipate.repository.AnswerRepository;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import com.partycipate.Partycipate.repository.SurveyRepository;
import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

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
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyElementRepository surveyElementRepository;

    @Autowired
    private UserService userService;

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


     /* getTimeResults for Survey and TimeLine
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
                List<TimeResultMcList> list = new ArrayList<>();
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
    public List<TimeResultMc> timeResultsForElement(int element_id, TimeLine timeLine){
//        get all answers for the survey_element
        Set<Answer> answerSet = answerRepository.getAnswersByElementId(element_id);
        Iterator<Answer> answerSetIt= answerSet.iterator();
        if (answerSetIt.hasNext()){
            //save every day in Set<ResultMc>
            List<TimeResultMc> timeResultMcSet = new ArrayList<>();
            Date start = trim(timeLine.getStart());
            Date end = trim(timeLine.getEnd());
            Date today = start;
//        iterate over Date until start is after end - start++
//        and create TimeResultMc
            while (today.compareTo(end) <= 0){
                log.info("TimeResult: Getting answers for day {}", today);
                //            iterate over answers and filter for current date

                Iterator<Answer> todayAnswers = filterByDate(answerSet,today).iterator();
                //            call helpermethod, that aggregates those results to ResultMc
                //            get ResultMc for the Day
                ResultMc resultMc = aggregateMcResults(todayAnswers, element_id);
                TimeResultMc timeResultMc;
                if(todayAnswers.hasNext()) {
                    timeResultMc = new TimeResultMc(today, resultMc);
                }
                else{
                    timeResultMc = new TimeResultMc(trim(today), resultMc);
                }
                log.info("TimeResult: adding results for {}", trim(timeResultMc.getDatetime()));
                timeResultMcSet.add(timeResultMc);
                Calendar c = Calendar.getInstance();
                c.setTime(today);
                c.add(Calendar.DATE, 7);
                today = c.getTime();

            }
            return timeResultMcSet;
        }
       else {
           return null;
        }


    }

    public Stream<Answer> filterByDate (Set<Answer> answerSet, Date today){
        return answerSet.stream().filter(a -> trim(a.getDate()).toInstant().equals(trim(today).toInstant()));
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
//        log.info("Timezone: {}", calendar.getTimeZone());
//        ToDo check for different TimeZones and SummerTimes
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.getTimeZone();

        return calendar.getTime();
    }


    /**
     * getAnswerCount for the relevant scope
     * <authors>
     *     <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     *     <author> Giovanni Carlucci </author>
     * </authors>
     * */
    public List<AnswerCount> getAnswerCountAllSurveys(TimeLine timeLine, User user){
        log.info("TimelineAnswers: Getting all answers for {} from {} to {}", user.getUsername(), timeLine.getStart(), timeLine.getEnd());
        Set<Integer> element_ids = new HashSet<>();
        Set<Integer> survey_ids = new HashSet<>();
        if (Boolean.TRUE.equals(userService.isAdmin())) {
            //get element_ids for all surveys
            element_ids = answerRepository.getDistinctElementIds();
        } else {
            survey_ids = surveyRepository.getDistinctSurveyIds(user.getUser_id());
            Iterator<Integer> surveyIter = survey_ids.iterator();
            while (surveyIter.hasNext()){
                Set<Integer> elements = surveyElementRepository.getSurveyElementsBySurveyId(surveyIter.next());
                Iterator<Integer> elementIter = elements.iterator();
                while (elementIter.hasNext()){
                    element_ids.add(elementIter.next());
                }
            }
        }
        //ToDo check return of DB Queries
        log.info("TimelineAnswers: Collected all relevant ElementIds in {}", element_ids);
//        we now have the list of surveyElements to get the answeres from
        List<AnswerCount> list = new ArrayList<>();

        // send list of elements
        list = aggregateAnswersForElements(element_ids, timeLine);
        log.info("TimelineAnswers: Retrieved aggregated AnswerCount in {}", list);
        // save and return list of AnswerCount and Dates
        return list;
    }

    /**
     * helperMethod getAnswerCount for all elements
     * <authors>
     *     <author> Jannik Sinz jannik.sinz@ibm.com </author>
     *     <author> Giovanni Carlucci </author>
     * </authors>
     */
    public List<AnswerCount> aggregateAnswersForElements(Set<Integer> elements, TimeLine timeLine){
        Date start = trim(timeLine.getStart());
        Date end = trim(timeLine.getEnd());
        Set<Answer> answers = new HashSet<>();
//        ToDo check return of DB queries, especially !Date!
        Iterator<Integer> elementIter = elements.iterator();
        while (elementIter.hasNext()){
            Set<Answer> elementAnswers = answerRepository.getAnswersByDateAndElement(start, end, elementIter.next());
//            store all those in a common AnswerSet that contains all relevant answers
            Iterator<Answer> elementAnswerIter = elementAnswers.iterator();
            while (elementAnswerIter.hasNext()){
                answers.add(elementAnswerIter.next());
            }
        }

        //log.info("TimelineAnswers: Collected ALL relevant answers(unsorted) in {}", answers);
        List<AnswerCount> list = new ArrayList<>();
//        Count through every Day
        Date today = trim(start);
        while (today.compareTo(end) <= 0){
            Stream stream = filterByDate(answers, today);
            int countAnswers = (int) filterByDate(answers, today).count();
            log.info("Date: {}", today);
            list.add(new AnswerCount(today, countAnswers));
            log.info("Date after: {}", today);

//            count up today
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DATE, 1);
            today = c.getTime();
        }
        return list;
    }
}