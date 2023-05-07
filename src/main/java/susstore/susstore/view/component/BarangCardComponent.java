package susstore.susstore.view.component;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import susstore.susstore.models.Barang;

public class BarangCardComponent {
    private final BorderPane componentRootLayout;
    private Barang barang;

    public BarangCardComponent(Barang barang) {
        this.componentRootLayout = new BorderPane();
        this.barang = barang;
        loadUI();
        setStyleSheet();
    }

    private void loadUI() {
        Rectangle imageContainer = new Rectangle(0, 0, 180, 180);
        imageContainer.getStyleClass().add("image-container");
        Image image = new Image(barang.getGambar(), false);
        imageContainer.setFill(new ImagePattern(image));
        Label nameLabel = new Label(barang.getNamaBarang());

        VBox imageAndNameContainer = new VBox();
        imageAndNameContainer.getStyleClass().add("image-name-container");
        imageAndNameContainer.getChildren().addAll(imageContainer, nameLabel);

        Button deleteButton = new Button("\uf2ed;");
        Button editButton = new Button("\ue4c7;");
        deleteButton.getStyleClass().add("delete-action-button");
        editButton.getStyleClass().add("edit-action-button");

        BorderPane actionsContainer = new BorderPane();
        actionsContainer.setLeft(editButton);
        actionsContainer.setRight(deleteButton);
        actionsContainer.getStyleClass().add("actions-container");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageAndNameContainer, actionsContainer);

        Label priceLabel = new Label(barang.getHargaBarang().getNominal()+"");
        Label stockLabel = new Label("Stock: " + barang.getStok());
        stockLabel.getStyleClass().add("stock-label");

        BorderPane priceAndStockContainer = new BorderPane();
        priceAndStockContainer.getStyleClass().add("price-stock-container");
        priceAndStockContainer.setLeft(priceLabel);
        priceAndStockContainer.setRight(stockLabel);

        this.componentRootLayout.setCenter(stackPane);
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
