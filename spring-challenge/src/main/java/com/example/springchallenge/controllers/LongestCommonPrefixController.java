package com.example.springchallenge.controllers;

import com.example.springchallenge.services.LongestCommonPrefixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/longestCommonPrefix")
public class LongestCommonPrefixController {

    // Ref: https://leetcode.com/problems/longest-common-prefix/

    @Autowired
    private LongestCommonPrefixService longestCommonPrefixService;

    @GetMapping("/myLongestCommonPrefix")
    public String myLongestCommonPrefix(
            @RequestParam String[] strs
    ) {
        return longestCommonPrefixService.myLongestCommonPrefix(strs);
    }

    @GetMapping("/top1LongestCommonPrefix")
    public String top1LongestCommonPrefix(
            @RequestParam String[] strs
    ) {
        return longestCommonPrefixService.top1LongestCommonPrefix(strs);
    }

}
