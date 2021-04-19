package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.repository.AnswerPossibilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerPossibilityService {
    private static final Logger log = LoggerFactory.getLogger(AnswerPossibilityService.class);

    @Autowired
    private AnswerPossibilityRepository answerPossibilityRepository;

    @Autowired
    public AnswerPossibilityService(AnswerPossibilityRepository answerPossibilityRepository) {
        this.answerPossibilityRepository = answerPossibilityRepository;
    }

    public int getCountOfAnswersPossibilities(int element_id){
        return answerPossibilityRepository.count_answer_possibilities(element_id);

    }
}
