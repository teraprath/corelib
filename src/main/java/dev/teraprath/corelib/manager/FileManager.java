package dev.teraprath.corelib.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import dev.teraprath.corelib.adapter.InterfaceAdapter;
import dev.teraprath.corelib.adapter.ItemStackAdapter;
import dev.teraprath.corelib.adapter.SuperclassExclusionStrategy;
import dev.teraprath.corelib.adapter.WorldAdapter;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.nio.file.Files;

public class FileManager {
    private JsonParser parser = new JsonParser();
    private Gson gson = new GsonBuilder().setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .serializeNulls()
            .registerTypeAdapter(World.class, new WorldAdapter())
            .registerTypeAdapter(BlockState.class, new InterfaceAdapter())
            .registerTypeAdapter(ItemStack.class, new ItemStackAdapter())
            .addDeserializationExclusionStrategy(new SuperclassExclusionStrategy())
            .addSerializationExclusionStrategy(new SuperclassExclusionStrategy())
            .create();

    private Object object;
    private Class clazz;
    private File file;

    public FileManager(Object object, File file) {
        this.object = object;
        this.file = file;
    }

    public FileManager(Class clazz, File file) {
        this.clazz = clazz;
        this.file = file;
    }

    public Object fromObject() {
        try {
            if(file.createNewFile()) {
                String json = gson.toJson(parser.parse(gson.toJson(object)));
                try(PrintWriter out = new PrintWriter(file)) {
                    out.println(json);
                }
            } else {
                Object test = gson.fromJson(new String(Files.readAllBytes(file.toPath())), object.getClass());

                if(test.getClass().equals(object.getClass())) {
                    return test;
                } else {
                    if(file.delete()) {
                        if(file.createNewFile()) {
                            String json = gson.toJson(parser.parse(gson.toJson(object)));
                            try(PrintWriter out = new PrintWriter(file)) {
                                out.println(json);
                            }
                        }
                        return test;
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object fromClass() {
        Object test = null;
        try {
            test = gson.fromJson(new String(Files.readAllBytes(file.toPath())), clazz);

            if(test.getClass().equals(clazz)) {
                return test;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveConfig() {
        try {
            if(file.createNewFile()) {
                String json = gson.toJson(parser.parse(gson.toJson(object)));
                try(PrintWriter out = new PrintWriter(file)) {
                    out.println(json);
                }
            } else {
                if(file.delete()) {
                    if(file.createNewFile()) {
                        String json = gson.toJson(parser.parse(gson.toJson(object)));
                        try(PrintWriter out = new PrintWriter(file)) {
                            out.println(json);
                        }
                    }

                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
