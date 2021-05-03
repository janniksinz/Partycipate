package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.AnswerPossibility;
import com.partycipate.Partycipate.repository.AnswerPossibilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnswerPossibilityService {
    private static final Logger log = LoggerFactory.getLogger(AnswerPossibilityService.class);

    @Autowired
    private AnswerPossibilityRepository answerPossibilityRepository;

    public int getCountOfAnswersPossibilities(int element_id){
        return answerPossibilityRepository.count_answer_possibilities(element_id);
    }

    public Set<AnswerPossibility> getAllByElementId(int element_id){
        return answerPossibilityRepository.findByElementId(element_id);
    }

    public int deleteById(int answerPossibility_id){
        answerPossibilityRepository.deleteById(answerPossibility_id);
        return answerPossibility_id;
    }
}
