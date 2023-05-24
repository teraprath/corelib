package dev.teraprath.corelib.manager;

import javax.annotation.Nonnull;
import javax.xml.stream.Location;
import java.util.HashMap;
import java.util.Map;

public class LocationManager {

    private final Map<String, Location> locations = new HashMap<>();

    public void setLocation(@Nonnull String name, @Nonnull Location location) {
        this.locations.put(name, location);
    }

    public Location getLocation(@Nonnull String name) {
        return this.locations.get(name);
    }

    public void remove(@Nonnull String name) {
        this.locations.remove(name);
    }

    public Map<String, Location> getLocations() {
        return this.locations;
    }

}
