package com.partycipate.Partycipate.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
        //ToDo access response body in {//When} to chenk for correct response message
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
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    void getSurvey() {
    }

    @Test
    void deleteSurveybyId() {
    }

    @Test
    void addSurveyElement() {
    }
}