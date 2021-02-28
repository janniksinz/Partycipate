package com.partycipate;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

@Client("/")
public interface AppClient {

    @Consumes(MediaType.TEXT_PLAIN)
    @Get("/")
    String home(@Header String authorization);

}
