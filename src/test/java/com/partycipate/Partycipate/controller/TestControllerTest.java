package com.partycipate.Partycipate.controller;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TestControllerTest {

    String admin_token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxNzQ4ODI1NSwiZXhwIjoxNjE3NTc0NjU1fQ.WfOFX98aKwoOlka3-G2KSBOM7l6w3Det8XATxg9a6XoIJSyay06ENMfE6JUsoJ2npw2C_Dls4rHPtdtOCGtlOg";
    String user_token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjE3NDg2NzM2LCJleHAiOjE2MTc1NzMxMzZ9.jwMRgRBSsWMhSf7aetT6l0wQ-f0S8y5vbfb-xC2A_qYV9VKU6UZOKVoFjlvhAnBE6Q4GDyRTg99oA9MZBIiMBg";


    @BeforeAll
    static void getAuth(){
        //ToDo add 4 requests to get temporary valid auth_tokens with admin, user credentials
        HttpUriRequest admin_request = new HttpPut("http://localhost:8088/api/auth/signup");

    }

    @Test
    void getEveryone() throws IOException {
//        Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/test/everyone");

//        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

//        Then
        assert(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK);
    }

    @Test
    void getAdmin() throws IOException {
//        Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/test/admin");
        request.setHeader(HttpHeaders.AUTHORIZATION, admin_token);
//        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
//        Then
        assert(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK);

    }

    @Test
    void getUserAdmin() {
    }

    @Test
    void getUser() throws IOException {
//        Given
        HttpUriRequest request = new HttpGet("http://localhost:8088/api/test/admin");
        request.setHeader(HttpHeaders.AUTHORIZATION, user_token);
//        When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
//        Then
        assert(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK);


    }
}