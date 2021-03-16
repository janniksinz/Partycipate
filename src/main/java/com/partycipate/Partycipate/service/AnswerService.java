package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.Result;
import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.AnswerPossibility;
import com.partycipate.Partycipate.model.MCAnswerContent;
import com.partycipate.Partycipate.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private McAnswerContentService mcAnswerContentService;

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

        for (int i =0; i<count;i++){
            counting_results.add(i, 0);
        }


        Iterator<Answer> iterator = answers.iterator();

        while (iterator.hasNext()){
            Answer a = iterator.next();

            Iterable<MCAnswerContent> mcac =  mcAnswerContentService.getAllMcAnswerContentByAnswerId(a.getId());

            Iterator<MCAnswerContent> mcacIterator = mcac.iterator();

            while(mcacIterator.hasNext()){

                MCAnswerContent content = mcacIterator.next();

                AnswerPossibility answerPossibility = content.getAnswerPossibility();

                int position = answerPossibility.getPosition(); //content.getPosition();

                int value = counting_results.get(position-1);

                value += 1;

                counting_results.set(position-1, value);

            }
        }

        Result result = new Result();
        result.setResults(counting_results);
        return  result;
        // Anmerkung
    }

}