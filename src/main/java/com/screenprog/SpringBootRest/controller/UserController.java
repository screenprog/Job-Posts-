package com.screenprog.SpringBootRest.controller;

import com.screenprog.SpringBootRest.model.User;
import com.screenprog.SpringBootRest.service.JwtService;
import com.screenprog.SpringBootRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtService jwtService;


    @PostMapping("register")
    public User register(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user){
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        else
            return "You are unauthenticated";
    }



}
