package com.example.springchallenge.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LongestCommonPrefixService {

    public String myLongestCommonPrefix(String[] strs) {
        List<String> result = new ArrayList<>();
        helper(strs, 0, result);

        return result.size() == 0 ? "" : String.join("", result);
    }

    private void helper(String[] strs, int round, List<String> result) {
        String temp = "";
        boolean breakLoop = false;

        for (int i=0; i<strs.length; i++) {
            if (breakLoop) {
                break;
            }

            if (strs.length == 1) {
                if (!strs[i].equals("")) {
                    result.add(strs[i]);
                }

            } else {
                try {
                    String txt = Character.toString(strs[i].charAt(round));

                    if (temp.equals("")) {
                        temp = txt;

                    } else {
                        if (temp.equals(txt)) {
                            if (i == strs.length - 1) {
                                round++;
                                result.add(txt);
                                helper(strs, round, result);
                            }
                        } else {
                            break;
                        }
                    }

                } catch (Exception e) {
                    breakLoop = true;
                    e.getStackTrace();
                }
            }
        }
    }

    public String top1LongestCommonPrefix(String[] strs) {
        int numStrs = strs.length;
        if (numStrs == 0) {
            return "";
        } else if (numStrs == 1) {
            return strs[0];
        }

        int indx = 0;
        int ans = -1;
        boolean done = false;
        do {
            int s = 0;
            char c = 0;
            for (s = 0; s < numStrs; ++s) {
                if (indx > strs[s].length() - 1) {
                    // This string has been exhausted
                    done = true;
                    break;
                }

                if (s == 0) {
                    c = strs[s].charAt(indx);
                    continue;
                }
                if (c != strs[s].charAt(indx)) {
                    done = true;
                    break;
                }
            }
            if (s == numStrs) {
                ans = indx;
                ++indx;
            }
        } while (!done);

        // Longest common prefix was till index 'ans'
        return strs[0].substring(0, ans+1);
    }

}
