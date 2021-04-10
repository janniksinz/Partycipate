package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.service.SurveyService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurveyControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void addSurvey() throws UnsupportedEncodingException {
        //ToDo add (header for auth
        //        Given
        HttpUriRequest request = null;

            StringEntity stringE = new StringEntity("{\"creation_date\": \"creation_date\", " +
                    "\"title\": \"title\", " +
                    "\"user_id\": 1, " +
                    "\"elements\": null}");
            stringE.setContentType("application/json;charset=utf-8");
            request = RequestBuilder.create("POST")
                    .setUri("http://localhost:8088/api/survey")
                    .setEntity(stringE)
                    .build();

//        When
        HttpResponse response = null;
        try {
            response = HttpClientBuilder.create().build().execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Then
        assertEquals( HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        //ToDo check for correct and incorrect auth (not possible yet)

    }
    @After
    //ToDo



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
    void getSurvey() throws IOException {
//        Given
        int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        int id = randomNum;
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/survey/" + id);
//        When
      HttpResponse response = HttpClientBuilder.create().build().execute(request);
//       Then
     assertEquals( HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
   }

    @Test
    void deleteSurveybyId() {
    }

    @Test
    void addSurveyElement() {
    }
}