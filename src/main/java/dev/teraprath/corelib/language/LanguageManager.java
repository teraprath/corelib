package dev.teraprath.corelib.language;

import dev.teraprath.corelib.manager.FileManager;
import dev.teraprath.corelib.utils.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LanguageManager {

    private final JavaPlugin plugin;
    private final Map<String, Language> languageMap;
    private String defaultLocale;

    public LanguageManager(@Nonnull JavaPlugin plugin) {
        this.plugin = plugin;
        this.languageMap = new HashMap<>();
        this.defaultLocale = "en_US";
        reload();
    }

    public void reload() {
        File folder = new File(plugin.getDataFolder(), "lang");
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            Language language = (Language) new FileManager(new Language(), file).fromObject();
            this.languageMap.put(file.toString().replace(".json", ""), language);
        }
    }

    public void saveDefaults(@Nonnull String locale) {

        File languageFolder = new File(plugin.getDataFolder(), "lang");
        if (!languageFolder.exists()) languageFolder.mkdirs();
        plugin.saveResource("lang/" + locale + ".json", false);

        reload();

    }

    public String getDefaultLocale() {
        return this.defaultLocale;
    }

    public void setDefaultLocale(@Nonnull String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public void send(@Nonnull final Player player, @Nonnull final String key) {
        player.sendMessage(Color.format(getPrefix(player.getLocale()) + get(player.getLocale(), key)));
    }

    public void send(@Nonnull final CommandSender sender, @Nonnull final String key) {
        sender.sendMessage(Color.format(getPrefix(getDefaultLocale()) + get(getDefaultLocale(), key)));
    }

    public String get(@Nonnull final String locale, @Nonnull final String key) {
        final Language language = this.languageMap.get(locale);
        return language.messages.get(key);
    }

    public String getPrefix(@Nonnull final String locale) {
        final Language language = this.languageMap.get(locale);
        return language.prefix;
    }


}
