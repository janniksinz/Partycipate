package com.partycipate.Partycipate.controller;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.partycipate.Partycipate.dto.ChangePassword;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/password")
    public String changePassword(@JsonProperty("email") String email, @JsonProperty("oldPW") String oldPW, @JsonProperty("newPW") String newPW){
        return userService.changePassword(email, oldPW,newPW);
    }
}
