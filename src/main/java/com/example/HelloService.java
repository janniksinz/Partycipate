package com.example;

import javax.inject.Singleton;

@Singleton
public class HelloService {

    public String sayHi(String name){
        User user = new User(name);

        return "Hello " + user.getName() + ". You are logged in!";
    }

}
