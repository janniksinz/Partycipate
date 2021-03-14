package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.model.AuthToken;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public void addUser(@RequestBody User user){
        userService.addUser(user.getEmail(),user.getPassword(), user.getUsername());
    }

}
