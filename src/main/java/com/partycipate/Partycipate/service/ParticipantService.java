package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendAnswer;
import com.partycipate.Partycipate.dto.SendMCAnswer;
import com.partycipate.Partycipate.model.*;
import com.partycipate.Partycipate.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipantService {
    private static final Logger log = LoggerFactory.getLogger(ParticipantService.class);

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private McAnswerContentRepository mcAnswerContentRepository;

    @Autowired
    private AnswerPossibilityRepository answerPossibilityRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private SurveyElementRepository surveyElementRepository;

    public Answer addAnswer(SendAnswer sendAnswer){
        //get participantId
        int Pid = sendAnswer.getParticipant_id();
        int Eid = sendAnswer.getSurveyElement_id();

//        get first Participant that matches Id
        Participant p = participantRepository.findById(Pid).stream().findFirst().get();
//        get first Element that matches Id
        SurveyElement sE = surveyElementRepository.findById(Eid).stream().findFirst().get();
//        save in new Answer with MCAnswerContent = null
        Answer answer = new Answer.Builder().mcAnswerContent(null).build();
        answer.setParticipant(p);
        answer.setSurveyElement(sE);
        answer.setDate(trim(sendAnswer.getDate()));
        // save answer
        answer = answerRepository.save(answer);
        int answerId = answer.getId();
        log.info("addAnswer: saved answer with id {}", answerId);

        // save MC Answers
        Set<SendMCAnswer> mcacS = sendAnswer.getSendMCAnswers();
        for (SendMCAnswer value : mcacS) {
            // get first AnswerPossibility that matches Id
            Optional<AnswerPossibility> dummyAnswerPSet = answerPossibilityRepository.findById(value.getAnswerPossibility_id()).stream().findFirst();
            AnswerPossibility dummyAnswerP = dummyAnswerPSet.get();
            // set AP and Answer
            MCAnswerContent mcAnswer = new MCAnswerContent.Builder().build();
            mcAnswer.setAnswer(answer);
            mcAnswer.setAnswerPossibility(dummyAnswerP);
            // save mcAnswer
            mcAnswerContentRepository.save(mcAnswer);
        }
        log.info("addAnswer: saved MCContent for Answer");
    return answer;
    }

    public Participant addParticipant(Participant participant){
        return participantRepository.save(participant);
    }

    public Optional<Participant> getParticipant(int participant_id){
        return participantRepository.findById(participant_id);
    }

    public static Date trim(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 2);

        return calendar.getTime();
    }
}
