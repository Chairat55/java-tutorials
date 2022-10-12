package com.example.springchallenge.controllers;

import com.example.springchallenge.services.RichestCustomerWealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/richestCustomerWealth")
public class RichestCustomerWealthController {

    // Ref: https://leetcode.com/problems/richest-customer-wealth

    @Autowired
    private RichestCustomerWealthService richestCustomerWealthService;

    @GetMapping("/myRichestCustomerWealth")
    public int myRichestCustomerWealth(
            @RequestParam int[][] accounts
    ) {
        return richestCustomerWealthService.myRichestCustomerWealth(accounts);
    }

    @GetMapping("/top1RichestCustomerWealth")
    public int top1RichestCustomerWealth(
            @RequestParam int[][] accounts
    ) {
        return richestCustomerWealthService.top1RichestCustomerWealth(accounts);
    }

}
