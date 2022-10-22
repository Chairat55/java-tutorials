package com.example.springchallenge.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RansomNoteService {

    public boolean myRansomNote(String ransomNote, String magazine) {
        int answer = 0;

        List<String> ransomNoteArray = Arrays.asList(ransomNote.split(""));
        List<String> magazineArray = new ArrayList<>(Arrays.asList(magazine.split("")));

        for (int i = 0; i < ransomNoteArray.size(); i++) {
            if (magazineArray.contains(ransomNoteArray.get(i))) {
                answer++;
                magazineArray.remove(ransomNoteArray.get(i));
            }
        }

        return ransomNoteArray.size() == answer;
    }

    public boolean top1RansomNote(String ransomNote, String magazine) {
        StringBuilder magazineSB = new StringBuilder(magazine);
        for(char c: ransomNote.toCharArray()){
            int index = magazineSB.indexOf(Character.toString(c));
            if(index==-1){
                return false;
            }else{
                magazineSB.delete(index,index+1); //get rid of used string
            }
        }
        return true;
    }

}
