package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.model.AuthToken;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public User addUser(@RequestBody User user){
        return userService.addUser(user.getEmail(),user.getPassword(), user.getUsername());
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable ("id") int id){
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable ("id") int id){
        userService.deleteUser(id);
    }

}
