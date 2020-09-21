package com.epam.rd.java.basic.practice3;


public class Part2 {

    public static void main(String[] args) {
        //method
    }

    public static String convert(String input) {
        int maxCount = 0;
        int minCount = Integer.MAX_VALUE;
        StringBuilder shortestWord = new StringBuilder();
        StringBuilder longestWord = new StringBuilder();
        String[] longestWordArray = input.split("[\\s-,']");
        String[] shortestWordArray = input.split("[\\s-,']");
        String shortWord;
        String longWord;

        for (String word : shortestWordArray) {
            if (word.length() <= minCount  && !word.contains("[^\\w]") && !word.equals("")) minCount = word.length();
        }
        for (String word : shortestWordArray) {
            if (word.length() == minCount && !shortestWord.toString().contains(word) && !word.contains("[^\\w]")) shortestWord.append(word + ", ");
        }
        shortWord = "Min: " + shortestWord.toString().substring(0, shortestWord.length() - 2);
        for (String word : longestWordArray) {
            if (word.length() >= maxCount) maxCount = word.length();
        }
        for (String word : longestWordArray) {
            if (word.length() == maxCount && !longestWord.toString().contains(word)) longestWord.append(word + ", ");
        }
        longWord = "Max: " + longestWord.toString().substring(0, longestWord.length() - 2);
        return shortWord + "\n" + longWord;
    }
}
