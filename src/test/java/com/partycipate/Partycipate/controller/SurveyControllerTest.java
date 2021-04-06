package com.partycipate.Partycipate.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurveyControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addSurvey() {
        //ToDo add (header for auth and) body for content in {//Given}
        //        Given
        HttpUriRequest request = null;
        try {
            request = RequestBuilder.create("POST")
                    .setUri("http://localhost:8088/api/survey")
                    .setEntity(new StringEntity("{\"cookie\": \"cookie\", " +
                            "\"creation_date\": \"creation_date\", " +
                            "\"title\": \"title\", " +
                            "\"user_id\": 1, " +
                            "\"elements\": null}"))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        When
        HttpResponse response = null;
        try {
            response = HttpClientBuilder.create().build().execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Then
        assertEquals( HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        //ToDo access response body in {//When} to check for correct response message
        //ToDo check for correct and incorrect input
        //ToDo check for correct and incorrect auth (not possible yet)
    }

    @Test
    void getAllSurveys() throws IOException {
//        Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/survey");
//        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
//        Then
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    void getSurvey() throws IOException {
//        Given
        HttpUriRequest request = RequestBuilder.create("POST")
                .setUri("http://localhost:8088/api/survey")
                .setEntity(new StringEntity("{\"a\", \"b\""))
//                Hier setzt ihr einen String ein, der dem Body in Postman entspricht.
//                ACHTUNG: 'und" werden vom Java interpretiert und müssen mit \ maskiert werden.
//                  Viel Spaß :)
                .build();
//        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
//        Then
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    void deleteSurveybyId() {
    }

    @Test
    void addSurveyElement() {
    }
}