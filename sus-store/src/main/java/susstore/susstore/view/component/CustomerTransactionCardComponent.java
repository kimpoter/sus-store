package susstore.susstore.view.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CustomerTransactionCardComponent {
    private final BorderPane componentRootLayout;

    public CustomerTransactionCardComponent() {
        this.componentRootLayout = new BorderPane();
        loadUI();
        setStyleSheet();
    }

    private void loadUI() {
        Label idLabel = new Label("999");
        Label nameLabel = new Label("Kurokawa Akane");
        Button infoButton = new Button("\uf05a;");
        Insets spacing = new Insets(10);
        idLabel.getStyleClass().add("label-customer-transaction-card");
        nameLabel.getStyleClass().add("label-customer-transaction-card");
        BorderPane.setMargin(idLabel, spacing);
        BorderPane.setMargin(nameLabel, spacing);
        BorderPane.setMargin(infoButton, spacing);
        BorderPane.setAlignment(idLabel, Pos.CENTER);
        BorderPane.setAlignment(nameLabel, Pos.CENTER_LEFT);
        this.componentRootLayout.setLeft(idLabel);
        this.componentRootLayout.setCenter(nameLabel);
        this.componentRootLayout.setRight(infoButton);
    }

    private void setStyleSheet() {
        this.componentRootLayout.getStyleClass().add("component-root-layout");
        this.componentRootLayout.getStylesheets().add("css/customer-transaction-card-component.css");
    }

    public Pane getComponent() {
        return this.componentRootLayout;
    }
}
