package com.partycipate.Partycipate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partycipate.Partycipate.repository.SurveyRepository;
import com.partycipate.Partycipate.repository.UserRepository;
import com.partycipate.Partycipate.service.SurveyElementService;
import com.partycipate.Partycipate.service.SurveyService;
import com.partycipate.Partycipate.service.UserService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SurveyControllerTest {
    private static final Logger log = LoggerFactory.getLogger(SurveyControllerTest.class);
    private static int survey_id;

    @BeforeEach
    void setUp(){
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addSurvey() throws IOException {
        //ToDo add (header for auth
    //Given
        //create request with body & user_id=1
        HttpUriRequest request;
        StringEntity stringE = new StringEntity("{\"creation_date\": \"creation_date\", " +
               "\"title\": \"title\", " +
                "\"user_id\": 4, " +
                "\"elements\": null}");
        stringE.setContentType("application/json;charset=utf-8");
        request = RequestBuilder.create("POST")
                .setUri("http://localhost:8088/api/survey")
                .setEntity(stringE)
                .build();

    //When
        HttpResponse response = null;
        try {
            response = HttpClientBuilder.create().build().execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    //Then
        survey_id = retrieveResourceFromResponse(response, Integer.class);
        log.info("Deleted Survey with ID of {}", survey_id);

        //Testing the Status Code
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode(), "Testing the Status Code failed");
        //Testing the Media Type of return body
        assertEquals("application/json", ContentType.getOrDefault(response.getEntity()).getMimeType(), "Testing the Media Type failed");
        //Testing the JSON Payload
        //ToDo check for correct and incorrect auth
    }

    @Test
    void getSurvey() throws IOException {
//        Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/survey/" +survey_id);
//        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
//       Then
        assertEquals( HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
    }

    @Test
    void deleteSurveyById() throws IOException {
    //Given
        HttpUriRequest request = new HttpDelete("http://localhost:8088/api/survey/" + survey_id);
        //        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
    //Then
        assertEquals( HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    void getAllSurveys() throws IOException {
    //Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/survey");
    //When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
    //Then
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }


    @Test
    void addSurveyElement() throws IOException {
//        Given

        HttpUriRequest request;
        StringEntity element = new StringEntity("{\"may_skip\": true, " +
                "\"position\": 1, " +
                "\"question\": \"Testquestion?\", " +
                "\"type\": \"single-choice\"," +
                "\"answer_possibilities\": null}");
        element.setContentType("application/json;charset=utf-8");
        request = RequestBuilder.create("POST")
                .setUri("http://localhost:8088/api/survey/element")
                .setEntity(element)
                .build();


        //        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
//       Then
        assertEquals( HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
    }

    public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> tClass)
            throws IOException {

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
                //.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, tClass);
    }

}