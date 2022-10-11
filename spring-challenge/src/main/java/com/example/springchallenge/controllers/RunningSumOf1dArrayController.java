package com.example.springchallenge.controllers;

import com.example.springchallenge.services.RunningSumOf1dArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/longestCommonPrefix")
public class RunningSumOf1dArrayController {

    // Ref: https://leetcode.com/problems/running-sum-of-1d-array/

    @Autowired
    private RunningSumOf1dArrayService runningSumOf1dArrayService;

    @GetMapping("/myRunningSumOf1dArray")
    public int[] myRunningSumOf1dArray(
            @RequestParam int[] nums
    ) {
        return runningSumOf1dArrayService.myRunningSumOf1dArray(nums);
    }

    @GetMapping("/top1RunningSumOf1dArray")
    public int[] top1RunningSumOf1dArray(
            @RequestParam int[] nums
    ) {
        return runningSumOf1dArrayService.top1myRunningSumOf1dArray(nums);
    }

}
