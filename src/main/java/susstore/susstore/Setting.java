package susstore.susstore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Setting {
    private static Setting instance;
    private String path;

    private final Configuration configuration;

    private Setting() {
        this.configuration = new Configuration("", new ArrayList<String>());
    }

    public static Setting getInstance() {
        if (instance == null)
            instance = new Setting();

        return instance;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setExtension(String extension) {
        this.configuration.extension = extension;
    }

    public void addPlugins(String plugin) {
        this.configuration.plugins.add(plugin);
    }

    public void load() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Configuration configuration = gson.fromJson(new FileReader(this.path), Configuration.class);

            this.configuration.extension = configuration.extension;
            this.configuration.plugins = configuration.plugins;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            Configuration configuration = new Configuration(
                    this.configuration.extension,
                    this.configuration.plugins
            );

            Gson gson = new Gson();

            Writer writer = Files.newBufferedWriter(Paths.get(this.path));

            gson.toJson(configuration, writer);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Configuration {
        private String extension;

        private ArrayList<String> plugins;

        public Configuration(String extension, ArrayList<String> plugins) {
            this.extension = extension;
            this.plugins = plugins;
        }
    }
}