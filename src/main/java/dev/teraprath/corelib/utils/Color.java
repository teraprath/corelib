package dev.teraprath.corelib.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {

    public static String format(String message) {
        message = Color.findAndReplaceRegex("&#[0-9,a-f,A-F]{6}", message);
        message = ChatColor.translateAlternateColorCodes(ChatColor.COLOR_CHAR, message);
        return message;
    }

    private static String findAndReplaceRegex(String regex, String input) {
        int i = 0;
        ArrayList<String> matches = new ArrayList<>();
        ArrayList<ChatColor> colorSet = new ArrayList<>();
        Matcher patternMatcher = Pattern.compile(regex).matcher(input);
        while(patternMatcher.find()) {
            matches.add(patternMatcher.group());
        }
        for(String match : matches) {
            colorSet.add(ChatColor.valueOf(match.substring(1)));
        }
        Iterator<String> matchIterator = matches.iterator();
        Iterator<ChatColor> colorIterator = colorSet.iterator();
        while(matchIterator.hasNext() && colorIterator.hasNext()) {
            input = input.replaceFirst(matchIterator.next(), colorIterator.next().toString());
        }
        return input;
    }

}
