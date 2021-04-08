package com.partycipate.Partycipate.service;

import com.nimbusds.jose.proc.SecurityContext;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserByJWT() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = getUserByUsername(username);
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    //getAllUsers
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(String email, String password){
        User user = new User.Builder().email(email).password(password).build();
        userRepository.save(user);
        return user;
    }
    public User getUser(int id){
        return userRepository.findById(id).get();
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}
