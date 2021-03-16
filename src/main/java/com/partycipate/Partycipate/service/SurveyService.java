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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Logger;


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
    public Survey addSurvey(SendSurvey surveyS){
        //create Iterator
        Set<SendElement> sendElements = surveyS.getElements();
        Iterator<SendElement> sendElementIterator= sendElements.iterator();
        //create surveyElementsSet
        Set<SurveyElement> surveyElements = new HashSet<>();
        //add SurveyElements to set
        int i=1;
        int lastApId= answerPossibilityRepository.getLastId()+1;
        int lastEId= surveyElementRepository.getLastId()+1;
        while (sendElementIterator.hasNext()){
            SendElement sendE= sendElementIterator.next();
            //create Iterator
            Set<SendAnswerPossibility> sAp= sendE.getAnswer_possibilities();
            Iterator<SendAnswerPossibility> sApIterator= sAp.iterator();
            //Create answerPossibilitySet

            Set<AnswerPossibility> aP= new HashSet<>();
            //add Answerpossibility to AnswerPossibilitySet

            while (sApIterator.hasNext()){
                SendAnswerPossibility sendAp= sApIterator.next();
                AnswerPossibility answerPossibility = new AnswerPossibility.Builder().id(lastApId).answer(sendAp.getAnswer()).position(sendAp.getPosition()).build();
                aP.add(answerPossibility);
                System.out.println("AnswerPossibilityId "+ answerPossibility.getId());
                lastApId++;
            }
            //create SurveyElemenr

            SurveyElement sE= new SurveyElement.Builder().id(lastEId).position(sendE.getPosition()).type(sendE.getType()).question(sendE.getQuestion()).may_skip(sendE.isMay_skip()).build();
            //Add AnswerPossibilitySet to SurveyElement

            sE.setAnswerPossibilities(aP);
            //Add SurveyElement to Set

            sE.setAnswers(null);
            surveyElements.add(sE);
            System.out.println("SurveyElementsId "+ sE.getId());
            lastEId++;
        }
        //create Set<Answerpossibility> from Set<SendAnswerPossibilities>
        //create Set<SurveyElements> from Set<SendSurveyElements>
        Survey survey = new Survey.Builder().creation_date(surveyS.getCreation_date()).title(surveyS.getTitle()).build();
        //Add SurveyElementsSet to Survey
        System.out.println(survey.getTitle());
        System.out.println(survey.getId());

        survey.setElements(surveyElements);
        //setUser to Survey
        survey.setUser(userService.getUser(surveyS.getUser_id()));
        //save Survey
        System.out.println(survey.getId()); //setIDs
        System.out.println("FirstElement: "+ survey.getElements().stream().findFirst());
        System.out.println(survey.getTitle());
        survey.setParticipantSet(null);
        Survey test = new Survey(4,"test","a","c",userService.getUser(1));

        surveyRepository.save(survey);
        System.out.println("test");
        return survey;
    }

    public @ResponseBody Iterable<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }

    //getSurveyBySurveyId
    public Survey getSurveyBySurveyId(int survey_id){ return surveyRepository.findById(survey_id); }



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



    public List<Survey> findAll() {
        return null;
    }


    public Survey getSurvey(int id) {
        return surveyRepository.findById(id);
    }
    /*public Iterable<Survey> getUserSurveys(int id){
        return (Iterable<Survey>) surveyRepository.findAllByUser_id(id);
    }*/
}
