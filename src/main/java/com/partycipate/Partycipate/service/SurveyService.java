package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendAnswerPossibility;
import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.dto.SendRangeAnswerPossibility;
import com.partycipate.Partycipate.dto.SendSurvey;
import com.partycipate.Partycipate.model.*;
import com.partycipate.Partycipate.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Service
public class SurveyService {
    private static final Logger log = LoggerFactory.getLogger(SurveyService.class);

    @Autowired
    private AnswerPossibilityRepository answerPossibilityRepository;
    @Autowired
    private SurveyElementRepository surveyElementRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RangeAnswerPossibilityRepository rangeAnswerPossibilityRepository;

    @Transactional
    public Survey addSurvey(SendSurvey surveyS, User user) {
        Survey survey = new Survey.Builder().creation_date(surveyS.getCreation_date()).title(surveyS.getTitle()).user(userService.getUser(user.getUser_id())).build();
        surveyRepository.save(survey);

        if(surveyS.getElements() !=null) {
            Set<SendElement> sendElements = surveyS.getElements();
            Iterator<SendElement> sendElementIterator = sendElements.iterator();
            Set<SurveyElement> surveyElements = new HashSet<>();

            while (sendElementIterator.hasNext()) {
                SendElement sendE = sendElementIterator.next();
                SurveyElement sE = new SurveyElement.Builder().position(sendE.getPosition()).type(sendE.getType()).question(sendE.getQuestion()).may_skip(sendE.isMay_skip()).build();
                sE.setSurvey(survey);

                surveyElementRepository.save(sE);

                if(sendE.getAnswer_possibilities() != null) {
                    Set<SendAnswerPossibility> sAp = sendE.getAnswer_possibilities();
                    Iterator<SendAnswerPossibility> sApIterator = sAp.iterator();

                    while (sApIterator.hasNext()) {
                        SendAnswerPossibility sendAp = sApIterator.next();
                        AnswerPossibility answerPossibility = new AnswerPossibility(sendAp.getAnswer(), sendAp.getPosition());
                        answerPossibility.setSurveyElement(sE);

                        answerPossibilityRepository.save(answerPossibility);
                    }
                }
//                range Possibility
                if(sendE.getRange_answer_possibilities() != null) {
                    Set<SendRangeAnswerPossibility> sRAp = sendE.getRange_answer_possibilities();
                    Iterator<SendRangeAnswerPossibility> sApIterator = sRAp.iterator();

                    while (sApIterator.hasNext()) {
                        SendRangeAnswerPossibility sendRAp = sApIterator.next();
                        RangeAnswerPossibility rangeAnswerPossibility = new RangeAnswerPossibility(sendRAp.getAnswer(), sendRAp.getPosition());
                        rangeAnswerPossibility.setSurveyElement(sE);

                        rangeAnswerPossibilityRepository.save(rangeAnswerPossibility);
                    }
                }

            }
        }

        return survey;
    }

    public Iterable<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }

    public Set<Survey> getAllSurveysByUser(User user){
        return surveyRepository.getSurveysByUser(user.getUser_id());
    }

    //getSurveyBySurveyId
    public Survey getSurveyBySurveyId(int survey_id) throws NullPointerException{ return surveyRepository.findById(survey_id); }

    /**
     * DELETE Survey*/
    public int deleteSurveybyId(int id) throws EmptyResultDataAccessException {
        try {
            surveyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Fail -> Survey not found");
        } catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
