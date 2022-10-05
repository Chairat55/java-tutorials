package com.example.springchallenge.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TwoSumService {

    public int[] myTwoSum(int[] nums, int target) {
        int[] result = {0, 0};

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }

        return result;
    }

    public int[] top1TwoSum(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (hash.containsKey(complement)) {
                int[] solution = new int[]{hash.get(complement), i};
                return solution;
            } else {
                hash.put(nums[i], i);
            }
        }
        return null;
    }

}
