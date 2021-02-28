package com.partycipate.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;

import javax.annotation.Nullable;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller()
@Client("/")
public class HomeController {

    @Produces(MediaType.TEXT_HTML)
    @Get
    @View("home")
    Map<String, Object> index(@Nullable Principal principal){
        Map<String, Object> data = new HashMap<>();
        data.put("loggedIn", principal != null);
        if (principal != null){
            data.put("username", principal.getName());
        }
        return data;
    }

}
