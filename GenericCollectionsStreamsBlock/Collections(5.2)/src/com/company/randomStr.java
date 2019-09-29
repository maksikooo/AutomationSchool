package com.company;

import java.util.Random;

public class randomStr {
    String generateRandomWord(int wordLength) {
        Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
        StringBuilder sb = new StringBuilder(wordLength);
        sb.append("Л");
        for(int i = 0; i < wordLength; i++) { // For each letter in the word
            char tmp = (char)(r.nextInt(26)+'а'); // Generate a letter between a and z
            sb.append(tmp); // Add it to the String
        }
        return sb.toString();
    }
}