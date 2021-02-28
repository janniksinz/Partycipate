package com.partycipate.serviceTests;

import com.partycipate.AppClient;
import com.partycipate.models.Survey;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.spock.annotation.MicronautTest;
import org.junit.Test;
import spock.lang.Shared;
import spock.lang.Specification;

import javax.inject.Inject;
import java.util.Base64;
import java.util.HashMap;


@MicronautTest
public class BasicAuthTest extends Specification {

    @Inject
    AppClient appClient;

    void verifyBasicAuthWorks(){
        String credsEncoded = Base64.getEncoder().encodeToString("user:password".getBytes());
        String rsp = appClient.home("Basic "+credsEncoded);
    }


//    @Inject
//    @Client("/")
//    RxHttpClient client;
//
//    @Shared
//    HttpResponse<String> rsp = client.toBlocking().exchange(HttpRequest.GET("/").accept(MediaType.TEXT_PLAIN).basicAuth("user", "password"), String.class);


}
