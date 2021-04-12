package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.SendAnswer;
import com.partycipate.Partycipate.dto.SendMCAnswer;
import com.partycipate.Partycipate.model.*;
import com.partycipate.Partycipate.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

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

    public ParticipantService(AnswerRepository answerRepository, McAnswerContentRepository mcAnswerContentRepository, AnswerPossibilityRepository answerPossibilityRepository, ParticipantRepository participantRepository, SurveyElementRepository surveyElementRepository) {
        this.answerRepository = answerRepository;
        this.mcAnswerContentRepository = mcAnswerContentRepository;
        this.answerPossibilityRepository = answerPossibilityRepository;
        this.participantRepository = participantRepository;
        this.surveyElementRepository = surveyElementRepository;
    }

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
        answer.setDate(sendAnswer.getDate());
        // save answer
        System.out.println("saving answer with null in mcAnswerContent");
        answer = answerRepository.save(answer);
        int answerId = answer.getId();
        System.out.println(answerId);

        // save MC Answers
        Set<SendMCAnswer> mcacS = sendAnswer.getSendMCAnswers();
        Iterator<SendMCAnswer> mcacSI= mcacS.iterator();
        while(mcacSI.hasNext()){
            SendMCAnswer value = mcacSI.next();
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
    return answer;
    }

    public Participant addParticipant(Participant participant){
        participantRepository.save(participant);
        return participant;

    }

    public Optional<Participant> getParticipant(int participant_id){
        return participantRepository.findById((Integer)participant_id);
    }
    
}
