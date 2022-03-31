package com.example.springoauth2.controllers;

import com.example.springoauth2.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public User getProfile(
            Authentication authentication
    ){
        return (User) authentication.getPrincipal();
    }

}
