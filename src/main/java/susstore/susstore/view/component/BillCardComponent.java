package susstore.susstore.view.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class BillCardComponent {
    private final BorderPane componentRootLayout;
    private final SimpleStringProperty initialAmount;
    private final String name;


    public BillCardComponent(SimpleStringProperty initialAmount, String name) {
        this.componentRootLayout = new BorderPane();
        this.initialAmount = initialAmount;
        this.name = name;
        loadUI();
        setStyleSheet();
    }

    private void loadUI() {
        Label amount = new Label();
        amount.textProperty().bind(initialAmount);
        Label x = new Label("x");
        Label namaBarang = new Label(this.name);
        HBox amountAndNamaBarangContainer = new HBox();
        amountAndNamaBarangContainer.getStyleClass().add("amount-nama-container-bill-card");
        amountAndNamaBarangContainer.getChildren().addAll(amount, x, namaBarang);

        Label priceLabel = new Label("30000");

        Insets spacing = new Insets(10);
        BorderPane.setMargin(amountAndNamaBarangContainer, spacing);
        BorderPane.setMargin(priceLabel, spacing);
        this.componentRootLayout.setLeft(amountAndNamaBarangContainer);
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
