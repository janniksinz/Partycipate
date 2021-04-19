package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.model.Role;
import com.partycipate.Partycipate.model.RoleName;
import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.RoleRepository;
import com.partycipate.Partycipate.repository.UserRepository;
import com.partycipate.Partycipate.security.jwt.JwtProvider;
import com.partycipate.Partycipate.security.message.request.LoginForm;
import com.partycipate.Partycipate.security.message.request.SignUpForm;
import com.partycipate.Partycipate.security.message.response.JwtResponse;
import com.partycipate.Partycipate.security.message.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest){
        //ToDo Check password and username, email with RegEx
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //ToDo (optional) extend authenication or principal to SetName into response
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PutMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpRequest){
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is aleady in use!"), HttpStatus.BAD_REQUEST);
        }

        //creating user account
        User user = new User.Builder().username(signUpRequest.getEmail()).email(signUpRequest.getEmail()).password(encoder.encode(signUpRequest.getPassword())).build();
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause : Admin User Role not found."));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail -> PM User Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail -> User Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User Registered successfully!"), HttpStatus.OK);
    }

}
