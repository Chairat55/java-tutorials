package com.example.springchallenge.controllers;

import com.example.springchallenge.services.TwoSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/twoSum")
public class TwoSumController {

    // Ref: https://leetcode.com/problems/two-sum/

    @Autowired
    private TwoSumService twoSumService;

    @GetMapping("/myTwoSum")
    public int[] myTwoSum(
            @RequestParam int[] nums,
            @RequestParam int target
    ) {
        return twoSumService.myTwoSum(nums, target);
    }

    @GetMapping("/top1TwoSum")
    public int[] top1TwoSum(
            @RequestParam int[] nums,
            @RequestParam int target
    ) {
        return twoSumService.top1TwoSum(nums, target);
    }

}
