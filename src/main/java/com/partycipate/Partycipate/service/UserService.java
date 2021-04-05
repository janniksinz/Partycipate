package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
