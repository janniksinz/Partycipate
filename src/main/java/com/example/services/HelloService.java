package com.example.services;

import com.example.models.User;

import javax.inject.Singleton;

@Singleton
public class HelloService {


    public String sayHi(String name){
        User user = new User(name, 1, "test@test.com");

        return "Hello " + user.getName() + ". You are logged in!";
    }

}