package com.partycipate.Partycipate.controller;

import com.partycipate.Partycipate.model.User;
import com.partycipate.Partycipate.repository.UserRepository;
import com.partycipate.Partycipate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllUser() throws Exception {

    }
    void addUser() {
    }

    @Test
    void getUser() {
        User user = new User();
        user.setUser_id(1);
        user.setUsername("Ursula");
        user.setEmail("uschiweb");
        user.setPassword("1234");

        when(userRepository.getUserById(anyInt())).thenReturn(user);

        User userReturn = userService.getUser(500);

        //if contains expected values
        assertNotNull(userReturn);
        assertEquals("Ursula", userReturn.getUsername());

    }

    @Test
    void deleteUser() {
    }
}