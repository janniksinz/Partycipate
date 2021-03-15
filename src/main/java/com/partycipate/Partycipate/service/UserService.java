package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.SurveyRepository;
import com.partycipate.Partycipate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public User addUser(String email, String password, String username){
        User user = new User.Builder().email(email).password(password).username(username).build();
        userRepository.save(user);
        return user;
    }
    public User getUser(int id){
        return userRepository.getUserById(id);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}
