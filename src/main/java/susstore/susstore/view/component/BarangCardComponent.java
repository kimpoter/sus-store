package susstore.susstore.view.component;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
import susstore.susstore.view.BarangCardType;

public class BarangCardComponent {
    private final BorderPane componentRootLayout;
    private final BarangCardType type;
    private final ObservableList<Node> bills;

    public BarangCardComponent(BarangCardType type, ObservableList<Node> bills) {
        this.componentRootLayout = new BorderPane();
        this.bills = bills;
        this.type = type;
        loadUI();
        setStyleSheet();
    }

    private void loadUI() {
        Rectangle imageContainer = new Rectangle(0, 0, 180, 180);
        imageContainer.getStyleClass().add("image-container");
        Image image = new Image("images/barang.jpg", false);
        imageContainer.setFill(new ImagePattern(image));
        Label nameLabel = new Label("Nama Barang");

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

        if (this.type.equals(BarangCardType.Kasir)) {
            stackPane.getChildren().remove(actionsContainer);

            SimpleIntegerProperty selectedAmount = new SimpleIntegerProperty(0);
            SimpleStringProperty selectedAmountString = new SimpleStringProperty();
            selectedAmountString.bind(Bindings.createStringBinding(() -> Integer.toString(selectedAmount.get()), selectedAmount));
            Label selectedAmountLabel = new Label("bakane");
            selectedAmountLabel.textProperty().bind(selectedAmountString);


            Button minusButton = new Button("\uf146;");
            Button plusButton = new Button("\uf0fe;");
            BorderPane addToChartActionContainer = new BorderPane();
            minusButton.getStyleClass().add("minus-button-barang-card");
            plusButton.getStyleClass().add("plus-button-barang-card");

            minusButton.setOnAction(event -> {
                if (selectedAmount.get() > 0) {
                    selectedAmount.set(selectedAmount.get() - 1);
                    this.bills.add(new Button("Add"));
                    this.bills.add(new BillCardComponent(selectedAmountString, "new barang").getComponent());
                }
            });
            plusButton.setOnAction(event -> {
                selectedAmount.set(selectedAmount.get() + 1);
            });

            addToChartActionContainer.setLeft(minusButton);
            addToChartActionContainer.setCenter(selectedAmountLabel);
            addToChartActionContainer.setRight(plusButton);
            addToChartActionContainer.getStyleClass().add("add-tochart-action-container-barang-card");

            this.componentRootLayout.setCenter(addToChartActionContainer);
        }

        Label priceLabel = new Label("Rp10000");
        Label stockLabel = new Label("Sotck: 99");
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
