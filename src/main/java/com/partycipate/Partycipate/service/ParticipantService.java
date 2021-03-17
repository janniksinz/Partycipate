package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendAnswer;
import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.Participant;
import com.partycipate.Partycipate.model.SurveyElement;
import com.partycipate.Partycipate.repository.AnswerRepository;
import com.partycipate.Partycipate.repository.McAnswerContentRepository;
import com.partycipate.Partycipate.repository.ParticipantRepository;
import com.partycipate.Partycipate.repository.SurveyElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    public McAnswerContentRepository mcAnswerContentRepository;

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
        // save in new Answer
        Answer answer = new Answer.Builder().build();
        answer.setParticipant(p);
        answer.setSurveyElement(sE);
        // save answer
        answerRepository.save(answer);

        // save MC Answers
        /*Set<MCAnswerContent> mcacS = answer.getMcAnswerContentSet();
        Iterator<MCAnswerContent> mcacSI= mcacS.iterator();
        while(mcacSI.hasNext()){
            mcAnswerContentRepository.save(mcacSI.next());
        }*/


    return answer;
    }

    public Participant addParticipant(Participant participant){
        participantRepository.save(participant);
        return participant;

    }

    
}
