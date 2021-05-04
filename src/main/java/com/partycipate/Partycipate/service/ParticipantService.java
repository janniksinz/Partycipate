package com.partycipate.Partycipate.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.partycipate.Partycipate.dto.*;
import com.partycipate.Partycipate.model.*;
import com.partycipate.Partycipate.repository.*;
import net.minidev.json.JSONObject;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import org.apache.http.impl.client.HttpClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    @Autowired
    private Survey_ParticipantRepository survey_participantRepository;


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

    public Participant addParticipant(Participant participant, int survey_id){
        participantRepository.save(participant);
        survey_participantRepository.sendAnswer(survey_id, participant.getId());
        return participant;
    }

    public Optional<Participant> getParticipant(int participant_id){
        return participantRepository.findById(participant_id);

    }

    public SendParticipant setParticipant(SubmitSurvey submitSurvey, String ipAdress){
        SendParticipant sendParticipant = new SendParticipant();
        System.out.println(submitSurvey.getParticipant_cookie());
        System.out.println(submitSurvey.getSurvey_id());
        System.out.println(submitSurvey.getLanguage());
//        paticipant exists
        if (submitSurvey.getParticipant_cookie() != null){

            Participant participant = participantRepository.getParticipantByCookie(submitSurvey.getParticipant_cookie());
            sendParticipant.setParticipant_id(participant.getId());
        }
//        create new participant
        else {
            Participant participant = new Participant();
            //generate cookie

            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            String originalString = participantRepository.getNextValue().toString();
            byte[] encodedhash = digest.digest(
                    originalString.getBytes(StandardCharsets.UTF_8));
            //get Region

            participant.setCookie(encodedhash.toString());
            participant.setRegion(getLocation(ipAdress));
            participant = addParticipant(participant, submitSurvey.getSurvey_id());
            sendParticipant.setParticipant_id(participant.getId());
            sendParticipant.setParticipant_cookie(participant.getCookie());

        }

        return sendParticipant;
    }





   /*   Deprecated
    public void getLocation2(String ip){
        String url= "https://geo.ipify.org/api/v1?apiKey=at_hslR7IDemhvyO1cGZc6iwZvci87PC&ipAddress=" +ip;


        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            try {
                log.info("rentCar: content = {}", response.getEntity().getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String responseBody = null;
            try {
                responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                log.info("ResponseBody: {}", responseBody);
                Map<String,Object> result = new ObjectMapper().readValue(response.getEntity().getContent(), HashMap.class);
                JSONObject obj = new JSONObject(result);
                Object score = obj.get("location");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }*/
    public String getLocation(String ip) {
        HttpUriRequest request = RequestBuilder.create("GET")
                .setUri("https://geo.ipify.org/api/v1?apiKey=at_hslR7IDemhvyO1cGZc6iwZvci87PC&ipAddress=" +ip)
                .build();
        String region="";
        try {
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            Map<String,Object> result = new ObjectMapper().readValue(response.getEntity().getContent(), HashMap.class);
            JSONObject obj = new JSONObject(result);
            String string = obj.toJSONString();
            SendApi sendApi = new ObjectMapper().readValue(string, SendApi.class);
            Location location =sendApi.getLocation();
            region = location.getCountry();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return region;
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
