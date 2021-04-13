package com.partycipate.Partycipate.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
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


class ParticipantControllerTest {
    private static int survey_id;
    private static int participant_id = 1;
    //Todo Fehler wegen Optional Objekt?
    @Test
    void getParticipant() throws IOException {
        //Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/participant/" + participant_id);
        //When
        HttpResponse response = null;
        try {
            response = HttpClientBuilder.create().build().execute(request);
            Pa
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response);
        System.out.println(response.getStatusLine().getStatusCode());
        //Then
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    void getBasicResults() throws IOException {
//        Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/participant/results/" +survey_id);
//        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
//       Then
        assertEquals( HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
    }

    //Todo
    @Test
    void addAnswer() throws IOException {
        //        Given

        HttpUriRequest request;
        StringEntity element = new StringEntity("{\"participant_id\": 1, " +
                "\"survey_element_id\": 1, " +
                "\"answer\": null}");
        element.setContentType("application/json;charset=utf-8");
        request = RequestBuilder.create("POST")
                .setUri("http://localhost:8088/api/participant/answer")
                .setEntity(element)
                .build();

        System.out.println(element);
        System.out.println(request);
        //        When

        HttpResponse response = null;
        try {
            response = HttpClientBuilder.create().build().execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        //       Then
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode(), "Testing the Status Code failed");
    }

}