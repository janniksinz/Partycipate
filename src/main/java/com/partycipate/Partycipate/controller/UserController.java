package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.dto.ChangePassword;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public @ResponseBody Iterable<User> getAllUser(){
        return userService.getAllUsers();
    }

    @PostMapping("")
    public User addUser(@RequestBody User user){
        return userService.addUser(user.getEmail(),user.getPassword());
    }
    @GetMapping("/{id}")
    public @ResponseBody User getUser(@PathVariable ("id") int id){
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable ("id") int id){
        userService.deleteUser(id);
        return id;
    }

    @PostMapping("/password")
    public String changePassword(@RequestBody ChangePassword changePassword){
        return userService.changePassword(changePassword.getEmail(), changePassword.getOldPassword(), changePassword.getNewPassword1(), changePassword.getNewPassword2());

    }

}
