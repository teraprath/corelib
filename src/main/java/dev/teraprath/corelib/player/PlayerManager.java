package dev.teraprath.corelib.player;


import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private final JavaPlugin plugin;
    private final Map<UUID, PlayerInfo> players;
    private final Map<String, Object> defaults;

    public PlayerManager(@Nonnull JavaPlugin plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
        this.defaults = new HashMap<>();
        plugin.getServer().getPluginManager().registerEvents(new PlayerListener(this), plugin);
    }

    public PlayerManager addDefaults(String key, Object value) {
        this.defaults.put(key, value);
        return this;
    }

    public void createPlayer(@Nonnull Player player) {
        this.players.put(player.getUniqueId(), new PlayerInfo(player, defaults));
    }

    public void deletePlayer(@Nonnull Player player) {
        this.players.remove(player.getUniqueId());
    }

    public Map<UUID, PlayerInfo> getPlayers() {
        return this.players;
    }

    public PlayerInfo getPlayerByUUID(@Nonnull UUID uuid) {
        return this.players.get(uuid);
    }

    public Map<String, Object> getDefaults() {
        return this.defaults;
    }

}
