package com.partycipate.Partycipate.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public User addUser(@RequestBody User user){
        return userService.addUser(user.getEmail(),user.getPassword());
    }

    @GetMapping("")
    public @ResponseBody User getUser() {
        return userService.getUserByJWT();
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable ("id") int id){
        userService.deleteUser(id);
        return id;
    }

    @PostMapping("/password")
    public String changePassword(@JsonProperty ("newpw") String newPw,
                                 @JsonProperty ("oldpw") String oldPw){
        return userService.changePassword(userService.getUserByJWT().getEmail(), oldPw, newPw);
    }



}
