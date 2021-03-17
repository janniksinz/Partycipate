package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.Result;
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
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Autowired
    private AnswerPossibilityService answerPossibilityService;

    @Autowired
    private McAnswerContentService mcAnswerContentService;

    @Autowired
    private SurveyElementService surveyElementService;

    @Autowired
    private AnswerService answerService;
    //getAnswersByElementId
    public Set<Answer> getAnswersByElementId(int id){
        Set<Answer> answers = answerRepository.getAnswersByElementId(id);
        return answers;
    }

    public Set<Result> getAllResultsForSurvey(int survey_id){
        Set<SurveyElement> sE = surveyElementService.getSurveyElementSetBySurveyID(survey_id);
        Iterator<SurveyElement> sEI = sE.iterator();
        Set<Result> results = new HashSet<Result>();
        while(sEI.hasNext()){
            int value = sEI.next().getId();
            System.out.println(value);
            // add Results from Element to Set<Results>
            results.add(answerService.results(value));
        }

        return results;
    }

    //TODO Results are not sorted
    public Result results(int element_id){
        //calculate all the answers to one Result to send back to the Frontend
        System.out.println("Start results ");
        int count = answerPossibilityService.getCountOfAnswersPossibilities(element_id);
        System.out.println("count "+ count);
        //brauche Set von Answers; ArrayList initialisen mit Laenge count; Iterator durchgehen und ArrayList hochzaehlen;
        // ArrayList in Result setzen (setResult)
        Set<Answer> answers = answerRepository.getAnswersByElementId(element_id);
        Result result = new Result();
        // count participants
        Optional<Integer> value1 = answerRepository.getCountParticipants(element_id);
        result.setCount_participants(value1.isPresent() ? value1.stream().iterator().next() : 0);
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


        result.setResults(counting_results);
        System.out.println("End of method ");
        return result;
        // Anmerkung
    }

}
