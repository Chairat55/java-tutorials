package com.example.springchallenge.services;

import org.springframework.stereotype.Service;

@Service
public class RichestCustomerWealthService {

    public int myRichestCustomerWealth(int[][] accounts) {
        int richest = 0;

        for (int i = 0; i < accounts.length; i++) {
            int temp = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                temp += accounts[i][j];
            }

            if (richest == 0) {
                richest = temp;

            } else if (temp > richest) {
                richest = temp;
            }
        }

        return richest;
    }

    public int top1RichestCustomerWealth(int[][] accounts) {
        int place = 0;
        for (int i = 0; i < accounts.length; i++) {
            int sum = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                sum += accounts[i][j];

                if (place < sum)
                    place = sum;
            }
            sum = 0;
        }
        return place;
    }

}
