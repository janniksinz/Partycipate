package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.Answer;
import com.partycipate.Partycipate.model.Participant;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.repository.AnswerRepository;
import com.partycipate.Partycipate.repository.ParticipantRepository;
import com.partycipate.Partycipate.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    public ParticipantService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Autowired
    private ParticipantRepository participantRepository;

    public Answer addAnswer(Answer answer){

        int id = answer.getParticipant().getId();
        if( participantRepository.existsById(id)){
            answerRepository.save(answer);
        }

        else{
            Participant participant = new Participant.Builder().id(id).build();
        }
    return answer;
    }
    public Participant addParticipant(Participant participant){
        participantRepository.save(participant);
        return participant;

    }

    
}
