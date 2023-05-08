package susstore.susstore;

import javafx.application.Application;
import javafx.stage.Stage;
import susstore.susstore.plugin.PluginManager;
import susstore.susstore.view.MainWindow;

import java.io.File;

public class MainApp extends Application {

    private static Setting setting = Setting.getInstance();
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        MainWindow.getInstance(stage);

        File settingFile = new File("sus-store/src/main/resources/settings.json");

        setting.setPath("sus-store/src/main/resources/settings.json");
        if (settingFile.exists())
        {
            setting.load();
            for (String path : setting.getListOfPlugins())
            {
                PluginManager.register(path);
            }
        }
    }
}