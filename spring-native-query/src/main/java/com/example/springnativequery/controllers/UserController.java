package com.example.springnativequery.controllers;

import com.example.springnativequery.dtos.UserDTO;
import com.example.springnativequery.entities.User;
import com.example.springnativequery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> list () {
        return userService.list();
    }

    @GetMapping("/native-query")
    public List<UserDTO> listFromNQ () {
        return userService.listFromNQ();
    }

}
