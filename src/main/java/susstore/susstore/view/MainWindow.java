package susstore.susstore.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import susstore.susstore.view.component.MenuBarComponent;
import susstore.susstore.view.component.TabsComponent;
import susstore.susstore.view.page.DashboardPage;

public class MainWindow {
    private final Stage primaryStage;

    public MainWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        loadUI();
    }

    private void loadUI() {
        BorderPane rootLayout = new BorderPane();
        StackPane stackLayout = new StackPane();

        new DashboardPage(stackLayout);
        TabsComponent tabs = new TabsComponent(stackLayout);
        new MenuBarComponent(rootLayout, tabs);

        rootLayout.setCenter(stackLayout);

        Scene scene = new Scene(rootLayout, 600, 400);
        scene.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/mainwindow.css");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(850);
        primaryStage.show();
    }
}
