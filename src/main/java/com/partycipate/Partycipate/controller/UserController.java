package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.ChangePassword;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;

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
    public String changePassword(@RequestBody ChangePassword changePassword){
        return userService.changePassword(userService.getUserByJWT().getEmail(), changePassword.getOldPW(), changePassword.getNewPW());
    }



}
