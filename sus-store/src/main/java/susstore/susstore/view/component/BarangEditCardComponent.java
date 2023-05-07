package susstore.susstore.view.component;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import susstore.susstore.controller.TemporaryBillController;
import susstore.susstore.models.Barang;
import susstore.susstore.models.TemporaryBill;
import susstore.susstore.models.TemporaryBillEntry;
import susstore.susstore.view.PageType;
import susstore.susstore.controller.EditBarangController;

import java.util.UUID;

public class BarangEditCardComponent {
    private final BorderPane componentRootLayout;
    private final PageType pageType;
    public int temporaryBillEntryIndex;
    private ObservableList<Node> bills;
    private TemporaryBill temporaryBill;
    private UUID userId;
    private Node billCard;
    private TemporaryBillController temporaryBillController;
    private Barang barang;
    private TemporaryBillEntry temporaryBillEntry;
    private SimpleBooleanProperty simpleBooleanProperty;
    private EditBarangController editBarangController;

    public BarangEditCardComponent(Barang barang, EditBarangController editBarangController) {
        this.barang = barang;
        this.pageType = PageType.AllBarang;
        this.bills = null;
        this.componentRootLayout = new BorderPane();
        this.editBarangController = editBarangController;
        loadUI();
        setStyleSheet();
    }

    private void loadUI() {
        Rectangle imageContainer = new Rectangle(0, 0, 180, 180);
        imageContainer.getStyleClass().add("image-container");
        Image image = new Image(barang.getPathGambar(), false);
        imageContainer.setFill(new ImagePattern(image));
        Label nameLabel = new Label(barang.getNama());

        VBox imageAndNameContainer = new VBox();
        imageAndNameContainer.getStyleClass().add("image-name-container");
        imageAndNameContainer.getChildren().addAll(imageContainer, nameLabel);

        Button deleteButton = new Button("\uf2ed;");
        Button editButton = new Button("\ue4c7;");
        deleteButton.getStyleClass().add("delete-action-button");
        editButton.getStyleClass().add("edit-action-button");

        editButton.setOnAction(
            e->{
                this.editBarangController.setBarang(barang);
            }
        );

        BorderPane actionsContainer = new BorderPane();
        actionsContainer.setLeft(editButton);
        actionsContainer.setRight(deleteButton);
        actionsContainer.getStyleClass().add("actions-container");


        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageAndNameContainer, actionsContainer);

        Label priceLabel = new Label(barang.getHargaJual() + "");
        Label stockLabel = new Label("Stock: " + barang.getStok());
        stockLabel.getStyleClass().add("stock-label");

        BorderPane priceAndStockContainer = new BorderPane();
        priceAndStockContainer.getStyleClass().add("price-stock-container");
        priceAndStockContainer.setLeft(priceLabel);
        priceAndStockContainer.setRight(stockLabel);

        this.componentRootLayout.setTop(stackPane);
        this.componentRootLayout.setBottom(priceAndStockContainer);
    }

    private void setStyleSheet() {
        this.componentRootLayout.getStyleClass().add("component-root-layout");
        this.componentRootLayout.getStylesheets().add("css/barang-card-component.css");
    }

    public Pane getComponent() {
        return this.componentRootLayout;
    }
}
