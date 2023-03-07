package com.labelinsight.codetest.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingLetters {
    private static final char[] alphArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final String[] alphStrArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static List<String> alphList = new ArrayList<String>();

    public String getMissingLetters(String input) {
        alphList.addAll(Arrays.asList(alphStrArray));

        input = input.toLowerCase();

        //char[] inputArr = input.toCharArray();

        List<String> alreadyFound = new ArrayList<String>();
        for (char c : alphArray) {
            // Check if all chars to look for are found, if so break loop
            if (alreadyFound.size() == alphArray.length) break;

            char[] temp = {c};
            String tempStr = String.copyValueOf(temp);

            // Found chars are replaced with empty char
            if (temp.equals("")) continue;

            // Add found chars to List for later use
            if (input.contains(tempStr)) {
                alreadyFound.add(tempStr);

                // Remove found chars from input so concurrent searches are quicker
                input.replace(c, ' ');
            }
        }

        // Remove each found letter from alphList for output
        for (String s : alreadyFound) {
            alphList.remove(s);
        }

        // Formatting output to a String instead of char array
        // Using StringBuffer instead of String concat since it will do this behind the scenes.. However, each time it
        // concats it creates a new StringBuilder/Buffer instantiation which uses more heap space
        StringBuffer output = new StringBuffer(alphList.size());
        for (String s : alphList) {
                output.append(s);
        }

        return output.toString();
    }


}
