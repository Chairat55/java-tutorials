package com.example.springchallenge.controllers;

import com.example.springchallenge.services.PalindromeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/palindromeNumber")
public class PalindromeNumberController {

    // Ref: https://leetcode.com/problems/palindrome-number

    @Autowired
    private PalindromeNumberService palindromeNumberService;

    @GetMapping("/myPalindromeNumber")
    public boolean myPalindromeNumber(
            @RequestParam int x
    ) {
        return palindromeNumberService.myPalindromeNumber(x);
    }

    @GetMapping("/top1PalindromeNumber")
    public boolean top1PalindromeNumber(
            @RequestParam int x
    ) {
        return palindromeNumberService.top1PalindromeNumber(x);
    }

}
