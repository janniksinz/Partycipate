package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendAnswer;
import com.partycipate.Partycipate.dto.SendMCAnswer;
import com.partycipate.Partycipate.model.*;
import com.partycipate.Partycipate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class ParticipantService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private McAnswerContentRepository mcAnswerContentRepository;

    @Autowired
    private AnswerPossibilityRepository answerPossibilityRepository;

    @Autowired
    public ParticipantService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private SurveyElementRepository surveyElementRepository;

    public Answer addAnswer(SendAnswer sendAnswer){
        //get participantId
        int Pid = sendAnswer.getParticipant_id();
        int Eid = sendAnswer.getSurveyElement_id();

        //get first Participant that matches Id
        Optional<Participant> participant = participantRepository.findById(Pid).stream().findFirst();
        Iterator<Participant> Piter = participant.stream().iterator();
        Participant p = Piter.next();
        //get first Element that matches Id
        Optional<SurveyElement> surveyElement = surveyElementRepository.findById(Eid).stream().findFirst();
        Iterator<SurveyElement> Eiter = surveyElement.stream().iterator();
        SurveyElement sE = Eiter.next();
        // save in new Answer with MCAnswerContent = null
        Answer answer = new Answer.Builder().mcAnswerContent(null).build();
        answer.setParticipant(p);
        answer.setSurveyElement(sE);
        // save answer
        answer = answerRepository.save(answer);
        int answerId = answer.getId();

        // save MC Answers
        Set<SendMCAnswer> mcacS = sendAnswer.getSendMCAnswers();
        Iterator<SendMCAnswer> mcacSI= mcacS.iterator();
        while(mcacSI.hasNext()){
            SendMCAnswer value = mcacSI.next();
            // get first Answer that matches Id
            Optional<Answer> dummyAnswerSet = answerRepository.findById(answerId).stream().findFirst();
            Answer dummyAnswer = dummyAnswerSet.stream().iterator().next();
            // get first AnswerPossibility that matches Id
            Optional<AnswerPossibility> dummyAnswerPSet = answerPossibilityRepository.findById(value.getAnswerPossibility_id()).stream().findFirst();
            AnswerPossibility dummyAnswerP = dummyAnswerPSet.stream().iterator().next();
            // set AP and Answer
            MCAnswerContent mcAnswer = new MCAnswerContent.Builder().answer(dummyAnswer).answerPossibility(dummyAnswerP).build();
            // save mcAnswer
            mcAnswerContentRepository.save(mcAnswer);
        }
    return answer;
    }

    public Participant addParticipant(Participant participant){
        participantRepository.save(participant);
        return participant;

    }

    
}
