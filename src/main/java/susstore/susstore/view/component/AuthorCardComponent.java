package susstore.susstore.view.component;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class AuthorCardComponent {
    private final VBox rootLayout;

    public AuthorCardComponent() {
        this.rootLayout = new VBox();
        loadUI();
    }

    private void loadUI() {
        Circle imagePlaceholder = new Circle(0, 0, 50);
        Image image = new Image("images/jihu-4.jpg", false);
        imagePlaceholder.setFill(new ImagePattern(image));
        imagePlaceholder.getStyleClass().add("imagePlaceholder");

        Label name = new Label("Name");

        this.rootLayout.getChildren().addAll(imagePlaceholder, name);
        this.rootLayout.getStylesheets().add("css/authorcardcomponent.css");
    }

    public VBox getRootLayout() {
        return this.rootLayout;
    }
}
