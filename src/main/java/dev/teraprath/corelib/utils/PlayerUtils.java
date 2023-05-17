package dev.teraprath.corelib.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class PlayerUtils {

    public static void reset(@Nonnull Player player) {
        player.setFlying(false);
        player.setAllowFlight(false);
        player.setGameMode(GameMode.SURVIVAL);
        player.setLevel(0);
        player.setExp(0);
        player.setTotalExperience(0);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.getInventory().clear();
    }

}
