package susstore.susstore.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PluginManager
{
    private static List<String> filePaths = new ArrayList<>();

    public static void register(String filePath) {
        // Add file to db
        filePaths.add(filePath);
        PluginLoader.load(filePath);
    }

    public static void remove(String filePath) {
        // Delete file from db, then reload all plugin
    }

    public static void enableAll() {
        // Get all file from db, then load all plugin
        for (String filePath : filePaths) {
            PluginLoader.load(filePath);
        }
    }
}
