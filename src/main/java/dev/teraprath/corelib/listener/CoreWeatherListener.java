package dev.teraprath.corelib.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class CoreWeatherListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent e) {
        boolean rain = e.toWeatherState();
        if (rain) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent e) {
        boolean storm = e.toThunderState();
        if (storm) {
            e.setCancelled(true);
        }
    }

}
