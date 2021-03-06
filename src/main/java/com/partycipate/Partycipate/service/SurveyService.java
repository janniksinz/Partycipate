package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendAnswerPossibility;
import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.dto.SendSurvey;
import com.partycipate.Partycipate.model.*;
import com.partycipate.Partycipate.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Service
public class SurveyService {
    private static final Logger log = LoggerFactory.getLogger(SurveyService.class);

    @Autowired
    private  AnswerRepository answerRepository;
    @Autowired
    private McAnswerContentRepository mcAnswerContentRepository;
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

    /**
     * addParticipant Reference to survey in DB
     * <author> Giovanni Carlucci - giovannicarlucci9@yahoo.de </author>
     * <author> Jannik Sinz  - jannik.sinz@ibm.com </author>
     * */
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

                if(sendE.getAnswer_possibilities()!= null || sendE.getAnswer_possibilities().size()!=0) {
                    Set<SendAnswerPossibility> sAp = sendE.getAnswer_possibilities();
                    Iterator<SendAnswerPossibility> sApIterator = sAp.iterator();

                    while (sApIterator.hasNext()) {
                        SendAnswerPossibility sendAp = sApIterator.next();
                        AnswerPossibility answerPossibility = new AnswerPossibility(sendAp.getAnswer(), sendAp.getPosition());
                        answerPossibility.setSurveyElement(sE);

                        answerPossibilityRepository.save(answerPossibility);
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
     * DELETE Survey
     * <author> Jannik Sinz  - jannik.sinz@ibm.com </author>
     * <author> Andreas Pitsch - wi19165@lehre.dhbw-stuttgart.de</author>
     * */
    public int deleteSurveybyId(int id) throws EmptyResultDataAccessException {
        try {
            Set<Integer> set = mcAnswerContentRepository.findAllBySurveyId(id);
            Iterator<Integer> iterator = set.iterator();
            while(iterator.hasNext()){
                mcAnswerContentRepository.deleteById(iterator.next());
            }
            set = answerRepository.findAllBySurveyId(id);
            iterator = set.iterator();
            while(iterator.hasNext()){
                answerRepository.deleteById(iterator.next());
            }
            surveyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Fail -> Survey not found");
        } catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public boolean ownsSurvey(int survey_id){
        User user = userService.getUserByJWT();
        int i = surveyRepository.ownsSurvey(user.getUser_id(), survey_id);
        return (i>=1);
    }
}
