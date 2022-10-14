package com.example.springchallenge.controllers;

import com.example.springchallenge.services.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fizzBuzz")
public class FizzBuzzController {

    // Ref: https://leetcode.com/problems/fizz-buzz/

    @Autowired
    private FizzBuzzService fizzBuzzService;

    @GetMapping("/myFizzBuzz")
    public List<String> myFizzBuzz(
            @RequestParam int n
    ) {
        return fizzBuzzService.myFizzBuzz(n);
    }

    @GetMapping("/top1FizzBuzz")
    public List<String> top1FizzBuzz(
            @RequestParam int n
    ) {
        return fizzBuzzService.top1FizzBuzz(n);
    }

}
