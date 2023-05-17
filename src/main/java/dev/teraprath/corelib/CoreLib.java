package dev.teraprath.corelib;

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
        if (instance != null) {
            instance.getServer().getPluginManager().registerEvents(listener, instance);
        }
    }

    public static void register(@Nonnull CommandExecutor executor, @Nonnull String command) {
        if (instance != null) {
            instance.getCommand(command).setExecutor(executor);
        }
    }


}
