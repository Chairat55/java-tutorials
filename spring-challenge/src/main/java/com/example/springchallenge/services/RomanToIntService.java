package com.example.springchallenge.services;

import org.springframework.stereotype.Service;

@Service
public class RomanToIntService {

    public int myRomanToInt(String s) {
        String[] romanArray = s.split("");
        int previousInt = 0;
        int total = 0;

        for (int i=0 ; i<romanArray.length ; i++) {
            int romanInt = convertTxtRomanToInt(romanArray[i]);

            if (previousInt == 0) {
                previousInt = romanInt;
                total += romanInt;
            } else {
                if (previousInt < romanInt) {
                    total += romanInt - previousInt * 2;
                } else {
                    total += romanInt;
                }
                previousInt = romanInt;
            }
        }

        return total;
    }

    private int convertTxtRomanToInt(String romanTxt) {
        int romanInt;

        if (romanTxt.equals("M")) romanInt = 1000;
        else if (romanTxt.equals("D")) romanInt = 500;
        else if (romanTxt.equals("C")) romanInt = 100;
        else if (romanTxt.equals("L")) romanInt = 50;
        else if (romanTxt.equals("X")) romanInt = 10;
        else if (romanTxt.equals("V")) romanInt = 5;
        else romanInt = 1;

        return romanInt;
    }

    public int top1RomanToInt(String s) {
        int current = 0;
        int answer = 0;
        int previous = 0;
        // Read from right to left
        for(int i = s.length() -1; i>=0; i--){
            switch(s.charAt(i)){
                case 'I':
                    current = 1;
                    break;
                case 'V':
                    current = 5;
                    break;
                case 'X':
                    current = 10;
                    break;
                case 'L':
                    current = 50;
                    break;
                case 'C':
                    current = 100;
                    break;
                case 'D':
                    current = 500;
                    break;
                case 'M':
                    current = 1000;
                    break;
            }
            if (previous > current){
                answer -=current;

            }else{
                answer += current;
                previous = current;
            }

        }
        return answer;
    }
}
