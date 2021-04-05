package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendAnswerPossibility;
import com.partycipate.Partycipate.dto.SendElement;
import com.partycipate.Partycipate.dto.SendSurvey;
import com.partycipate.Partycipate.model.AnswerPossibility;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.model.SurveyElement;
import com.partycipate.Partycipate.repository.AnswerPossibilityRepository;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import com.partycipate.Partycipate.repository.SurveyRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
public class SurveyService {

    @Autowired
    private AnswerPossibilityRepository answerPossibilityRepository;

    @Autowired
    private SurveyElementRepository surveyElementRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Transactional
    public Survey addSurvey(SendSurvey surveyS) {
        Survey survey = new Survey(surveyS.getCreation_date(),surveyS.getTitle(),surveyS.getCookie(),userService.getUser(surveyS.getUser_id()));
        surveyRepository.save(survey);

        Set<SendElement> sendElements = surveyS.getElements();
        Iterator<SendElement> sendElementIterator= sendElements.iterator();
        Set<SurveyElement> surveyElements = new HashSet<>();

        while (sendElementIterator.hasNext()) {
            SendElement sendE = sendElementIterator.next();
            SurveyElement sE = new SurveyElement.Builder().position(sendE.getPosition()).type(sendE.getType()).question(sendE.getQuestion()).may_skip(sendE.isMay_skip()).build();
            sE.setSurvey(survey);

            surveyElementRepository.save(sE);

            Set<SendAnswerPossibility> sAp = sendE.getAnswer_possibilities();
            Iterator<SendAnswerPossibility> sApIterator = sAp.iterator();

            while (sApIterator.hasNext()){
                SendAnswerPossibility sendAp= sApIterator.next();
                AnswerPossibility answerPossibility = new AnswerPossibility(sendAp.getAnswer(), sendAp.getPosition());
                answerPossibility.setSurveyElement(sE);

                answerPossibilityRepository.save(answerPossibility);
            }
        }

        return survey;
    }

    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }

    //getSurveyBySurveyId
    public Survey getSurveyBySurveyId(int survey_id) throws NullPointerException{ return surveyRepository.findById(survey_id); }



    public void deleteSurveybyId(int id)  {
        try {
            surveyRepository.deleteById(id);
        }
        catch (Exception e){
            System.err.println("Id not found");
            e.printStackTrace();
        }
    }




    public static Survey getRandomSurvey(int id) {
        //ToDo create content for the dummy survey
        return new Survey.Builder().id(0).creation_date("2021-02-28T18:25:43.511Z").cookie("11111222222333333").title("some Survey").build();
    }
}
