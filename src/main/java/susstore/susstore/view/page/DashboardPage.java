package susstore.susstore.view.page;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import susstore.susstore.view.component.AuthorCardComponent;
import susstore.susstore.view.component.DateTimeComponent;

public class DashboardPage {
    private final VBox layout;
    private final Pane rootLayout;
    private final HBox authorLayout;
    private final DateTimeComponent dateTimeComponent;


    public DashboardPage(Pane rootLayout) {
        this.layout = new VBox();
        this.rootLayout = rootLayout;
        this.authorLayout = new HBox();
        this.dateTimeComponent = new DateTimeComponent();
        loadUI();
    }

    private void loadUI() {
        this.layout.setAlignment(Pos.CENTER);
        this.layout.getChildren().add(this.dateTimeComponent.getRootLayout());

        this.authorLayout.setAlignment(Pos.CENTER);
        for (int i = 0; i < 5; i++) {
            this.authorLayout.getChildren().add(new AuthorCardComponent().getRootLayout());
        }
        this.authorLayout.getStyleClass().add("hbox-layout");

        this.layout.getChildren().add(this.authorLayout);
        this.layout.getStyleClass().add("vbox-layout");

        this.rootLayout.getChildren().add(this.layout);
        this.rootLayout.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/dashboardpage.css");
    }

    private void dateTimeNode() {

    }

    public VBox getLayout() {
        return this.layout;
    }
}
