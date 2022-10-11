package com.example.springchallenge.services;

import org.springframework.stereotype.Service;

@Service
public class RunningSumOf1dArrayService {

    public int[] myRunningSumOf1dArray(int[] nums) {
        int[] result = new int[nums.length];

        for (int i=1; i<=nums.length ; i++) {
            int sum = 0;
            for (int j=0; j<i; j++) {
                sum += nums[j];
            }

            result[i-1] = sum;
        }

        return result;
    }

    public int[] top1myRunningSumOf1dArray(int[] nums) {
        int[] result = new int[nums.length];

        result[0] = nums[0];
        for(int i=1; i<nums.length; i++)
        {
            result[i] = result[i-1] + nums[i];
        }
        return result;
    }

}
