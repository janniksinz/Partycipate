package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.AdminChangePw;
import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import com.partycipate.Partycipate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user/pw")
    public ResponseEntity<?> changePassword(@RequestBody AdminChangePw adminChangePw){
        if (Boolean.TRUE.equals(userService.isAdmin())) {
            return userService.changePassword(adminChangePw.getEmail(), adminChangePw.getOldPw(), adminChangePw.getNewPw());
        } else {
            return new ResponseEntity<>(new ResponseMessage("Fail -> you have no authority to change PW over this endpoint"), HttpStatus.BAD_REQUEST);
        }
    }
}
