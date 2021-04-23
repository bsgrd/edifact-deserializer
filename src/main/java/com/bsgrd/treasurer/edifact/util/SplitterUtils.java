package com.bsgrd.treasurer.edifact.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class SplitterUtils {

    private SplitterUtils() {
    }

    public static List<String> split(final String string, final String separator, final String escapeCharacter) {
        String regex = "(?<!" + Pattern.quote(escapeCharacter) + ")" + Pattern.quote(separator);
        return Arrays.stream(string.split(regex))
                .map(x -> x.replaceAll(Pattern.quote(escapeCharacter + separator), separator))
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
