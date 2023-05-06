package susstore.susstore;

import javafx.application.Application;
import javafx.stage.Stage;
import susstore.susstore.view.MainWindow;


public class MainApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        new MainWindow(stage);
    }
}