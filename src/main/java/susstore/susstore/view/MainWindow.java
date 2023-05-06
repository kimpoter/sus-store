package susstore.susstore.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import susstore.susstore.view.component.Navbar;
import susstore.susstore.view.page.DashboardPage;
import susstore.susstore.view.page.PageManager;

public class MainWindow {
    private final Stage primaryStage;
    private final BorderPane rootLayout;
    private final StackPane contentLayout;
    private final Navbar navbar;
    private final PageManager pageManager;

    public MainWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.rootLayout = new BorderPane();
        this.contentLayout = new StackPane();
        this.pageManager = new PageManager(primaryStage);
        this.navbar = new Navbar(pageManager);
        loadUI();
    }

    private void loadUI() {

        new DashboardPage(this.contentLayout);
        this.contentLayout.getChildren().add(this.pageManager.getTabPane());

        this.rootLayout.setTop(this.navbar.getMenuBar());
        this.rootLayout.setCenter(this.contentLayout);

        Scene scene = new Scene(this.rootLayout, 600, 400);
        scene.getStylesheets().add("css/mainwindow.css");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }
}
