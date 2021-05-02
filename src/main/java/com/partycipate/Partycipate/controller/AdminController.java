package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.AdminChangeUser;
import com.partycipate.Partycipate.dto.AdminChangePw;
import com.partycipate.Partycipate.dto.TimeLine;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import com.partycipate.Partycipate.service.AnswerService;
import com.partycipate.Partycipate.service.ParticipantService;
import com.partycipate.Partycipate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    ParticipantService participantService;

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public @ResponseBody ResponseEntity<?> getUser(@PathVariable("id") int user_id){
        User user = userService.getUser(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/pw")
    public ResponseEntity<?> changePassword(@RequestBody AdminChangePw changePw){
        if (Boolean.TRUE.equals(userService.isAdmin())) {
            User user = userService.getUser(changePw.getId());
            return new ResponseEntity<>(userService.changePassword(user, changePw.getOldPw(), changePw.getNewPw()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Fail -> you have no authority to change PW over this endpoint"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int user_id){
        User user = userService.getUser(user_id);
        return new ResponseEntity<>(userService.deleteUser(user), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> changeUser(@RequestBody AdminChangeUser changeUser){
        User user = userService.getUser(changeUser.getId());
        return new ResponseEntity<>(userService.changeUser(user, changeUser), HttpStatus.OK);
    }

    //total number of Partycipants
    @GetMapping("/participants")
    public ResponseEntity<?> getCountParticipants(@RequestBody TimeLine timeline){
        User user = userService.getUserByJWT();
        return new ResponseEntity<>(answerService.getAnswerCountAllSurveys(timeline, user), HttpStatus.OK);
    }


}
