package susstore.susstore.view.component;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DateTimeComponent {
    private final VBox rootLayout;

    public DateTimeComponent() {
        this.rootLayout = new VBox();
        loadUI();
    }

    private void loadUI() {

        // create labels
        Label labelTanggal = new Label("Sun, 30 February 2024");
        Label labelJam = new Label("12 : 12 : 12");
        labelTanggal.getStyleClass().add("labelTanggal");
        labelJam.getStyleClass().add("labelJam");

        this.rootLayout.getChildren().addAll(labelTanggal, labelJam);
        this.rootLayout.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/datetimecomponent.css");
    }

    public VBox getRootLayout() {
        return this.rootLayout;
    }
}
