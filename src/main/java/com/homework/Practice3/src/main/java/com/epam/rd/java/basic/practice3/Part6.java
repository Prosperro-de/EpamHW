package com.epam.rd.java.basic.practice3;

import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        
    }

    public static String convert(String input) {
        return Pattern.compile("(\\b\\w+\\b)(?=[\\s\\S]*\\b\\1\\b[\\s\\S]*\\b\\1\\b)", Pattern.UNICODE_CHARACTER_CLASS).matcher(input + " " + input).replaceAll("_$1").substring(0, Pattern.compile("(\\b\\w+\\b)(?=[\\s\\S]*\\b\\1\\b[\\s\\S]*\\b\\1\\b)", Pattern.UNICODE_CHARACTER_CLASS).matcher(input + " " + input).replaceAll("_$1").length() - 1 - Pattern.compile("(\\b\\w+\\b)(?=[\\s\\S]*\\b\\1\\b[\\s\\S]*\\b\\1\\b)", Pattern.UNICODE_CHARACTER_CLASS).matcher(input).replaceAll("_$1").length());
    }
}
