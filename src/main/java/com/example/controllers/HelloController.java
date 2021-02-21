package com.example.controllers;

import com.example.services.HelloService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;

@Controller("/")
public class HelloController {

    private final HelloService helloService;

    @Inject
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Get(value = "/login/{name}", produces = MediaType.TEXT_PLAIN)
    public String sayHi(String name){
        return helloService.sayHi(name);
    }

    @Get(value = "/", produces = MediaType.TEXT_PLAIN)
    public String welcome(){
        return "Welcome to Partycipate";
    }

}
