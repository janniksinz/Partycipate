package com.partycipate.Partycipate.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantControllerTest {

    @Test
    void getSurvey() throws IOException {
        // Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/particpant/1" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Then
        System.out.println(response.getEntity());
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    void getBasicResults() {
    }

    @Test
    void addAnswer() {
    }
}