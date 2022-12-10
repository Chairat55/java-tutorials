package com.example.springelastic.controllers;

import com.example.springelastic.entities.documents.UserDocument;
import com.example.springelastic.entities.User;
import com.example.springelastic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/elastic")
    public List<UserDocument> listFromElastic(
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false) Integer ageFrom,
            @RequestParam(required = false) Integer ageTo
    ) {
        return userService.listFromElastic(username, ageFrom, ageTo);
    }

    @PutMapping("/elastic/re-index")
    public void reIndex() {
        userService.reIndex();
    }

    @PostMapping("/init")
    public void initUser() {
        userService.initUser();
    }

}
