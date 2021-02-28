package com.partycipate.controllers;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/login")
public class LoginAuthController {

    @Produces(MediaType.TEXT_HTML)
    @Get("/auth")
    @View("auth")
    public Map<String, Object> auth(){
        return new HashMap<>();
    }

    @Produces(MediaType.TEXT_HTML)
    @Get("/authFailed")
    @View("auth")
    public Map<String, Object> authFailed(){
        return Collections.singletonMap("errors", true);
    }



}
