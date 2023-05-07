package susstore.susstore.view.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class BillCardComponent {
    private final BorderPane componentRootLayout;

    public BillCardComponent() {
        this.componentRootLayout = new BorderPane();
        loadUI();
        setStyleSheet();
    }

    private void loadUI() {
        Label amountAndNamaBarangLabel = new Label("2 x Pulpen Muji");
        Label priceLabel = new Label("30000");

        Insets spacing = new Insets(10);
        BorderPane.setMargin(amountAndNamaBarangLabel, spacing);
        BorderPane.setMargin(priceLabel, spacing);
        this.componentRootLayout.setLeft(amountAndNamaBarangLabel);
        this.componentRootLayout.setRight(priceLabel);
    }

    private void setStyleSheet() {
        this.componentRootLayout.getStyleClass().add("component-root-layout");
        this.componentRootLayout.getStylesheets().add("css/bill-card-component.css");
    }

    public Pane getComponent() {
        return this.componentRootLayout;
    }
}
