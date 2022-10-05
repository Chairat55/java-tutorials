package com.example.springchallenge.services;

import org.springframework.stereotype.Service;

@Service
public class PalindromeNumberService {

    public boolean myPalindromeNumber(int x) {
        if (x < 0) {
            return false;

        } else {
            String temp = Integer.toString(x);
            int[] numbers = new int[temp.length()];
            for (int i = 0; i < temp.length(); i++) {
                numbers[i] = temp.charAt(i) - '0';
            }

            for (int j = 1; j <= numbers.length; j++) {
                if (numbers[j-1] != numbers[numbers.length-j]) {
                    return false;
                }
            }

            return true;
        }
    }

    public boolean top1PalindromeNumber(int x) {
        if(x<0 || (x%10==0 && x!=0))
            return false;
        if(x<10)
            return true;

        int result = 0;
        int temp = x;

        while(x>0){
            result = result*10 + (x%10);
            x/=10;
        }

        return (result==temp);
    }

}
