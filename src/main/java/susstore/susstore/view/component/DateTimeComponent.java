package susstore.susstore.view.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
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

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);

                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE, dd MMMM yyyy");
                    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH : mm : ss");

                    LocalDateTime now = LocalDateTime.now();

                    Platform.runLater(() -> {
                        labelTanggal.setText(now.format(dateFormat));
                        labelJam.setText(now.format(timeFormat));
                    });
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        });
        thread.start();

        this.rootLayout.getChildren().addAll(labelTanggal, labelJam);
        this.rootLayout.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/datetimecomponent.css");
    }

    public VBox getRootLayout() {
        return this.rootLayout;
    }
}
