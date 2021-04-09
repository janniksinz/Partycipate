package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

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

    //TODO implement check password rules
    public String changePassword(String email, String oldPassword, String newPassword1, String newPassword2){
        //check oldPassword (have to hash Password to check
        if(userRepository.existsByEmail(email)){
            if (encoder.matches(oldPassword, userRepository.getPassword(email))){
                //Check newPasswords are equal
                if(newPassword1.equals(newPassword2)){
                    //Check Passwords rules?
                    if (true){
                        //HashPassword and Insert into Database
                        String newPassword=encoder.encode(newPassword1);
                        userRepository.changePassword(newPassword, email);
                        return "Success";
                    }
                    else{
                        return "Password rules are not satisfyied";
                    }
                }
                else{

                    return "Passwords are not equal";
                }
            }
            else{
                return "old Password is wrong";
            }
        }
        else{
            return "User Does not Exist";
        }
    }
}
