package com.example.springchallenge.controllers;

import com.example.springchallenge.services.RomanToIntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/romanToInt")
public class RomanToIntController {

    /*
        Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000

        For example, 2 is written as II in Roman numeral, just two ones added together.
        12 is written as XII, which is simply X + II.
        The number 27 is written as XXVII, which is XX + V + II.

        Roman numerals are usually written largest to smallest from left to right.
        However, the numeral for four is not IIII. Instead, the number four is written as IV.
        Because the one is before the five we subtract it making four.
        The same principle applies to the number nine, which is written as IX.
        There are six instances where subtraction is used:

            I can be placed before V (5) and X (10) to make 4 and 9.
            X can be placed before L (50) and C (100) to make 40 and 90.
            C can be placed before D (500) and M (1000) to make 400 and 900.

        Given a roman numeral, convert it to an integer.

        Constraints:
            1 <= s.length <= 15
            s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
            It is guaranteed that s is a valid roman numeral in the range [1, 3999].

        Testcase:
            "III"
            "LVIII"
            "MCMXCIV"
    */

    @Autowired
    private RomanToIntService romanToIntService;

    @GetMapping("/myRomanToInt")
    public int myRomanToInt(
            @RequestParam String s
    ) {
        return romanToIntService.myRomanToInt(s);
    }

    @GetMapping("/top1RomanToInt")
    public int top1RomanToInt(
            @RequestParam String s
    ) {
        return romanToIntService.top1RomanToInt(s);
    }
}
