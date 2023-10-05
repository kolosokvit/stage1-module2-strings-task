package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder regex = new StringBuilder("[");
        for (String s : delimiters) {
            regex.append(s).append("|");
        }
        List<String> tokens = new ArrayList<>(List.of(source.split(regex.substring(0, regex.length() - 1) + "]+")));
        if (tokens.get(0).isEmpty()) {
            tokens.remove(0);
        }
        return tokens;
    }
}
