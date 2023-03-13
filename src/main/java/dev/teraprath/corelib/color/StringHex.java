package dev.teraprath.corelib.color;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHex {

    public static String format(String str) {
        str = ChatColor.translateAlternateColorCodes(ChatColor.COLOR_CHAR, str);
        str = findAndReplaceRegex("!#[0-9,a-f,A-F]{6}", str);
        str = findAndReplaceRegex("&#[0-9,a-f,A-F]{6}", str);
        str = ChatColor.translateAlternateColorCodes('&', str);
        return str;
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
            colorSet.add(ChatColor.of(match.substring(1)));
        }
        Iterator<String> matchIterator = matches.iterator();
        Iterator<ChatColor> colorIterator = colorSet.iterator();
        while(matchIterator.hasNext() && colorIterator.hasNext()) {
            input = input.replaceFirst(matchIterator.next(), colorIterator.next().toString());
        }
        return input;
    }

    public static String rainbow(String str) {
        StringBuilder finalizedMessage = new StringBuilder();
        int hue = 0;
        for(int messageChar = 0; messageChar < str.toCharArray().length; messageChar++) {
            Color color = Color.getHSBColor(((float)hue/360), 1f, 1f);
            String red = Integer.toHexString(color.getRed());
            String green = Integer.toHexString(color.getGreen());
            String blue = Integer.toHexString(color.getBlue());

            String hexColor = "!#" + (red.length() <= 2 ? StringUtils.repeat( "0",2-red.length()) + red : red) +
                    (green.length() <= 2 ? StringUtils.repeat( "0",2-green.length()) + green : green) +
                    (blue.length() <= 2 ? StringUtils.repeat( "0",2-blue.length()) + blue : blue);
            finalizedMessage.append(hexColor).append(str.toCharArray()[messageChar]);
            hue += (360/str.toCharArray().length);
        }
        return format(finalizedMessage.toString());
    }

    public static String gradient(String str, Color startColor, Color endColor) {
        StringBuilder finalizedMessage = new StringBuilder();
        for(int messageChar = 0; messageChar < str.toCharArray().length; messageChar++) {
            float ratio = (float) messageChar / (float) str.toCharArray().length;
            int red = (int) (endColor.getRed() * ratio + startColor.getRed() * (1 - ratio));
            int green = (int) (endColor.getGreen() * ratio + startColor.getGreen() * (1 - ratio));
            int blue = (int) (endColor.getBlue() * ratio + startColor.getBlue() * (1 - ratio));
            Color stepColor = new Color(red, green, blue);
            String redHex = Integer.toHexString(stepColor.getRed());
            String greenHex = Integer.toHexString(stepColor.getGreen());
            String blueHex = Integer.toHexString(stepColor.getBlue());

            String hexColor = "!#" + (redHex.length() <= 2 ? StringUtils.repeat( "0",2-redHex.length()) + redHex : redHex) +
                    (greenHex.length() <= 2 ? StringUtils.repeat( "0",2-greenHex.length()) + greenHex : greenHex) +
                    (blueHex.length() <= 2 ? StringUtils.repeat( "0",2-blueHex.length()) + blueHex : blueHex);
            finalizedMessage.append(hexColor).append(str.toCharArray()[messageChar]);
        }
        return format(finalizedMessage.toString());
    }

    public static Color parse(String hexColor) {
        hexColor = hexColor.replaceAll("&", "");
        if(!hexColor.startsWith("#")) {
            hexColor = "#" + hexColor;
        }
        return ChatColor.of(hexColor).getColor();
    }

}
