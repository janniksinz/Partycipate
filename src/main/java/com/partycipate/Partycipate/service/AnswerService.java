package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.Result;
import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.MCAnswerContent;
import com.partycipate.Partycipate.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

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


    //getAnswersByElementId
    public Set<Answer> getAnswersByElementId(int id){
        Set<Answer> answers = answerRepository.getAnswersByElementId(id);
        return answers;
    }


    public Result results(int element_id){
        //calculate all the answers to one Result to send back to the Frontend
        int count = answerPossibilityService.getCountOfAnswersPossibilities(element_id);
        //brauche Set von Answers; ArrayList initialisen mit Laenge count; Iterator durchgehen und ArrayList hochzaehlen;
        // ArrayList in Result setzen (setResult)
        Set<Answer> answers = answerRepository.getAnswersByElementId(element_id);
        ArrayList<Integer> counting_results = new ArrayList<>(count);
        Iterator<Answer> iterator = answers.iterator();
        while (iterator.hasNext()){
            Answer a = iterator.next();
            Set<MCAnswerContent> mcac = a.getMcAnswerContentSet();
            Iterator<MCAnswerContent> mcacIterator = mcac.iterator();
            while(mcacIterator.hasNext()){
                MCAnswerContent content = mcacIterator.next();
                int position = content.getPosition();
                int value = content.getContent();
                value += counting_results.get(position);
                counting_results.add(position, value);
            }
        }
        Result result = new Result();
        result.setResults(counting_results);
        return  result;
        // Anmerkung
    }
}