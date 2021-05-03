package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.AdminChangeUser;
import com.partycipate.Partycipate.model.Survey;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.SurveyRepository;
import com.partycipate.Partycipate.repository.UserRepository;
import com.partycipate.Partycipate.security.jwt.JwtProvider;
import com.partycipate.Partycipate.security.message.response.JwtResponse;
import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private SurveyService surveyService;


    /**
     * getUser from current Session (ContextHolder)
     * <authors>
     *     <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     * </authors>
     * */
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
    public List<User> getAllUsers(){
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
        Set<Survey> surveys = surveyRepository.getSurveysByUser(user.getUser_id());
        if (!surveys.isEmpty()) {
            for (Survey s: surveys) {
                surveyService.deleteSurveybyId(s.getId());
            }
        }
        userRepository.deleteById(user.getUser_id());
        return user;
    }

    /**
     * create new auth JWT token for user
     * <authors>
     *     <author> Jannik Sinz - jannik.sinz@ibm.com </author>
     * </authors>
     * */
    public Authentication renewAuth(User user){
        Authentication previousAuth = SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), previousAuth.getAuthorities());
//        re-authenticate

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    /**
     * changing user details & creds
     * <authors> Jannik Sinz - jannik.sinz@ibm.com </authors>
     * */
    public ResponseEntity<?> changeUser(User user, AdminChangeUser changeUser){
        //log.info("changeUserDetails: for User {}: {} into {}, {}", user.getUser_id(), user.getUsername(), changeUser.getName(), changeUser.getEmail());
        if(userRepository.existsById(user.getUser_id())){
//                match email rules
            if (changeUser.getEmail().matches("(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))") && changeUser.getName().matches("^(([A-Za-z0-9_-]{0,30})[ ]?)*([A-Za-z0-9_-]{0,30})?$")){

                userRepository.changeUser(user.getUser_id(), changeUser.getEmail(), changeUser.getName());
                User user1 = userRepository.findById(user.getUser_id());
                Authentication authentication = renewAuth(user1);
                UserDetails userDetails = userDetailsService.loadUserByUsername(user1.getUsername());
                log.info("changeUserDetails: SecurityContext {}", SecurityContextHolder.getContext().getAuthentication());
                return new ResponseEntity<>(new JwtResponse(jwtProvider.generateJwtTokenFromUser(authentication), userDetails.getUsername(), authentication.getAuthorities()), HttpStatus.OK);
            } else return new ResponseEntity<>(new ResponseMessage("Fail -> EmailRules or NameRules didn't match"), HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(new ResponseMessage("Fail -> User doesn't exist"), HttpStatus.BAD_REQUEST);
    }

    /**
     * changePassword for User
     * <authors>
     *     <author> Giovanni Carlucci </author>
     * </authors>
     * */
    public ResponseEntity<?> changePassword(User user, String oldPassword, String newPassword){
        log.info("changePW: changing PW for user {}: {}", user.getUser_id(), user.getUsername());
        //check oldPassword (have to hash Password to check
        if(userRepository.existsById(user.getUser_id())){
            if (encoder.matches(oldPassword, userRepository.getPassword(user.getEmail()))){

                    //Check Passwords rules?
                    if (newPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{10,}$")){
                        //HashPassword and Insert into Database
                        String encodedPassword=encoder.encode(newPassword);
                        userRepository.changePassword(encodedPassword, user.getEmail());
                        return new ResponseEntity<>(new ResponseMessage("Success -> Password Changed"), HttpStatus.OK);
                    }
                    else{
                        return new ResponseEntity<>(new ResponseMessage("Fail -> PasswordRules didn't match"), HttpStatus.BAD_REQUEST);
                    }
            }
            else{
                return new ResponseEntity<>(new ResponseMessage("Fail -> Old Password is wrong"), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            return new ResponseEntity<>(new ResponseMessage("Fail -> User doesn't exist"), HttpStatus.BAD_REQUEST);
        }
    }
}
