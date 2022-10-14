package com.example.springchallenge.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FizzBuzzService {

    public List<String> myFizzBuzz(int n) {
        List<String> result = new ArrayList<>();

        for (int i=1; i<=n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");

            } else if (i % 3 == 0) {
                result.add("Fizz");

            } else if (i % 5 == 0) {
                result.add("Buzz");

            } else {
                result.add(String.valueOf(i));
            }
        }

        return result;
    }

    public List<String> top1FizzBuzz(int n) {
        ArrayList<String> ans=new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            String a="FizzBuzz",b="Fizz",c="Buzz";
            if(i%3==0 && i%5==0)
                ans.add(a);
            else if(i%3==0)
                ans.add(b);
            else if(i%5==0)
                ans.add(c);
            else
                ans.add(i+"");
        }
        return ans;
    }

}
