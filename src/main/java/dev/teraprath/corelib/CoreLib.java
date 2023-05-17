package dev.teraprath.corelib;

import dev.teraprath.corelib.sql.SQLAuthentication;
import dev.teraprath.corelib.utils.HexUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public class CoreLib {

    private static JavaPlugin instance;

    public static void init(@Nonnull JavaPlugin plugin) {
        instance = plugin;
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static void register(@Nonnull Listener listener) {
        instance.getServer().getPluginManager().registerEvents(listener, instance);
    }

    public static void register(@Nonnull CommandExecutor executor, @Nonnull String command) {
        instance.getCommand(command).setExecutor(executor);
    }

    public static String format(String message) {
        message = ChatColor.translateAlternateColorCodes(ChatColor.COLOR_CHAR, message);
        message = HexUtils.findAndReplaceRegex("!#[0-9,a-f,A-F]{6}", message);
        message = HexUtils.findAndReplaceRegex("&#[0-9,a-f,A-F]{6}", message);
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }


}
