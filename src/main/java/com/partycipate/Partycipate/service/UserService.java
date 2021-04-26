package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.AdminChangeUser;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.UserRepository;
import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserByJWT() {
        User user = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            user = getUserByUsername(username);
        } catch (Exception e){
            e.printStackTrace();
            log.info("Fail -> SecurityContext not found");
        }
        return user;
    }
    /**
     * isAdmin
     * <auhtor> Jannik Sinz - jannik.sinz@ibm.com </auhtor>
     * */
    public Boolean isAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterator<? extends GrantedAuthority> iter = authentication.getAuthorities().stream().iterator();
        Boolean admin = false;
        while (iter.hasNext()){
            GrantedAuthority auth = iter.next();
            if(Boolean.TRUE.equals(auth.toString().equals("ROLE_ADMIN"))) admin=true;
        }
        return admin;
    }

    /**
     * getUserByUsername
     * <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     * */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    //getAllUsers
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        User user1 = new User.Builder().email(user.getEmail()).password(user.getPassword()).build();
        userRepository.save(user1);
        return user1;
    }
    public User getUser(int id){
        return userRepository.findById(id);
    }
    public User deleteUser(User user){
        userRepository.deleteById(user.getUser_id());
        return user;
    }

    public ResponseEntity<?> changeUser(User user, AdminChangeUser changeUser){
        log.info("changeEmail: for User {}: {}", user.getUser_id(), user.getUsername());
        if(userRepository.existsById(user.getUser_id())){
            if (user.getEmail()==changeUser.getOldEmail()){
//                match email rules
                if (user.getEmail().matches("(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))")){
                    userRepository.changeEmail(user.getUser_id(), changeUser.getNewEmail());
                    return new ResponseEntity<>(userRepository.changeEmail(user.getUser_id(), changeUser.getNewEmail()), HttpStatus.OK);
                } else throw new RuntimeException("Fail -> EmailRules didn't match");
            } else throw new RuntimeException("Fail -> Old Emails don't match");
        } else throw new RuntimeException("Fail -> User doesn't exist");
    }

    public ResponseEntity<?> changePassword(User user, String oldPassword, String newPassword1){
        log.info("changePW: changing PW for user {}: {}", user.getUser_id(), user.getUsername());
        //check oldPassword (have to hash Password to check
        if(userRepository.existsById(user.getUser_id())){
            if (encoder.matches(oldPassword, userRepository.getPassword(user.getEmail()))){

                    //Check Passwords rules?
                    if (oldPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")){
                        //HashPassword and Insert into Database
                        String newPassword=encoder.encode(newPassword1);
                        userRepository.changePassword(newPassword, user.getEmail());
                        return new ResponseEntity<>(new ResponseMessage("Success -> Password Changed"), HttpStatus.OK);
                    }
                    else{
                        throw new RuntimeException("Fail -> PasswordRules didn't match");
                    }
            }
            else{
                throw new RuntimeException("Fail ->  Old Password is wrong");
            }
        }
        else{
            throw new RuntimeException("Fail -> User doesn't exist");
        }
    }
}
