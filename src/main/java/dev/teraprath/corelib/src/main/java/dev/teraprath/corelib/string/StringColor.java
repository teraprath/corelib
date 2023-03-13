package dev.teraprath.corelib.string;

import org.bukkit.ChatColor;

public class StringColor {

    public static String format(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

}
