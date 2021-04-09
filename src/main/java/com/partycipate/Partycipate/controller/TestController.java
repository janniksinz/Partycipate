package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {


   @GetMapping("/everyone")
   public ResponseEntity<?> getEveryone(){
       return new ResponseEntity<>(new ResponseMessage(">>> For Everyone"), HttpStatus.OK);
   }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<?> getAdmin(){
       return new ResponseEntity<>(new ResponseMessage(">>> For Admins only"), HttpStatus.OK);
   }


    @GetMapping("/user_admin")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserAdmin(){
       return new ResponseEntity<>(new ResponseMessage(">>> Users and Admins only"), HttpStatus.OK);
   }


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUser(){
        return new ResponseEntity<>(new ResponseMessage(">>> For Users only"), HttpStatus.OK);
    }


}
