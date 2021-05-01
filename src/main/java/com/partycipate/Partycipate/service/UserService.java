package com.partycipate.Partycipate.service;

import com.partycipate.Partycipate.dto.AdminChangeUser;
import com.partycipate.Partycipate.model.User;
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
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private SessionRegistry sessionRegistry;

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

    public Authentication renewAuth(User user){
//        invalidate - just for good practise, we don't actually use sessionRegistry yet
        // invalidate user session
        List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
        log.info("All Pricipals: {}", loggedUsers);
        for (Object principal : loggedUsers) {
            if(principal instanceof User) {
                final User loggedUser = (User) principal;
                if(user.getUsername().equals(loggedUser.getUsername())) {
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if(null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            log.info("Exprire now : {}", sessionInformation.getSessionId());
                            sessionInformation.expireNow();
                            sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                            // User is not forced to re-logging
                        }
                    }
                }
            }
        }
//        re-authenticate
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return newAuth;
    }

    public ResponseEntity<?> changeUser(User user, AdminChangeUser changeUser){
        log.info("changeUserDetails: for User {}: {} into {}, {}", user.getUser_id(), user.getUsername(), changeUser.getEmail(), changeUser.getName());
        if(userRepository.existsById(user.getUser_id())){
//                match email rules
            if (changeUser.getEmail().matches("(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))") && changeUser.getName().matches("^(([A-Za-z0-9_-]{0,30})[ ]?)*([A-Za-z0-9_-]{0,30})?$")){

                userRepository.changeUser(user.getUser_id(), changeUser.getEmail(), changeUser.getName());
                Authentication newAuth = renewAuth(user);
                UserDetails userDetails = (UserDetails) newAuth.getPrincipal();
                return new ResponseEntity<>(new JwtResponse(jwtProvider.generateJwtToken(newAuth), userDetails.getUsername(), userDetails.getAuthorities()), HttpStatus.OK);
            } else return new ResponseEntity<>(new ResponseMessage("Fail -> EmailRules or NameRules didn't match"), HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(new ResponseMessage("Fail -> User doesn't exist"), HttpStatus.BAD_REQUEST);
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
