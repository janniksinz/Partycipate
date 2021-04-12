package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.UserChangePw;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

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

    @DeleteMapping("")
    public ResponseEntity<?> deleteUser(){
        User user = userService.getUserByJWT();
        return new ResponseEntity<>(userService.deleteUser(user).getUser_id(), HttpStatus.OK);
    }

    @PostMapping("/pw")
    public ResponseEntity<?> changePassword(@RequestBody UserChangePw userChangePw){
        User user = userService.getUserByJWT();
        return userService.changePassword(user, userChangePw.getOldPw(), userChangePw.getNewPw());
    }



}
