package com.example.springchallenge.controllers;

import com.example.springchallenge.services.RansomNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ransomNote")
public class RansomNoteController {

    // Ref: https://leetcode.com/problems/ransom-note/

    @Autowired
    private RansomNoteService ransomNoteService;

    @GetMapping("/myRansomNote")
    public boolean myRansomNote(
            @RequestParam String ransomNote,
            @RequestParam String magazine
    ) {
        return ransomNoteService.myRansomNote(ransomNote, magazine);
    }

    @GetMapping("/top1RansomNote")
    public boolean top1RansomNote(
            @RequestParam String ransomNote,
            @RequestParam String magazine
    ) {
        return ransomNoteService.top1RansomNote(ransomNote, magazine);
    }

}
