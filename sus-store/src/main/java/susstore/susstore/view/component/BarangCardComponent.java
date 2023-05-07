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
import susstore.susstore.view.PageType;

public class BarangCardComponent {
    private final BorderPane componentRootLayout;
    private final PageType pageType;
    public int temporaryBillEntryIndex;
    private ObservableList<Node> bills;
    private TemporaryBill temporaryBill;
    private int userId;
    private Node billCard;
    private TemporaryBillController temporaryBillController;
    private Barang barang;
    private TemporaryBillEntry temporaryBillEntry;
    private SimpleBooleanProperty simpleBooleanProperty;

    public BarangCardComponent(Barang barang) {
        this.barang = barang;
        this.pageType = PageType.AllBarang;
        this.bills = null;
        this.componentRootLayout = new BorderPane();
        loadUI();
        setStyleSheet();
    }

    public BarangCardComponent(int userId, Barang barang, ObservableList<Node> bills, TemporaryBillController temporaryBillController, TemporaryBill temporaryBill, SimpleBooleanProperty simpleBooleanProperty) {
        this.componentRootLayout = new BorderPane();
        this.bills = bills;
        this.barang = barang;
        this.userId = userId;
        this.temporaryBill = temporaryBill;
        this.pageType = PageType.Kasir;
        this.temporaryBillController = temporaryBillController;
        this.simpleBooleanProperty = simpleBooleanProperty;
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

        if (this.pageType.equals(PageType.Kasir)) {
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

            if (this.temporaryBill != null) {
                System.out.println("TEMPBILL: " + this.temporaryBill.getDaftar().size());
                int i = 0;
                for (TemporaryBillEntry temporaryBillEntry : this.temporaryBill.getDaftar()) {
                    System.out.println("TEMPORARYBILL ENTRY ID: " + temporaryBillEntry.getBarang().getId());
                    System.out.println("BARANG ID: " + this.barang.getId());
                    if (temporaryBillEntry.getBarang().getId() == this.barang.getId()) {
                        selectedAmount.set(temporaryBillEntry.getJumlah());
                        this.billCard = new BillCardComponent(selectedAmountString, barang).getComponent();
                        this.bills.add(this.billCard);
                        this.temporaryBillEntry = temporaryBillEntry;
                        this.temporaryBillEntryIndex = i;
                    }
                    i++;
                }
                System.out.println("THIS BILL SIZE: " + this.bills.size());
            }

            minusButton.setOnAction(event -> {
                if (selectedAmount.get() > 0) {
                    selectedAmount.set(selectedAmount.get() - 1);
                    this.temporaryBillEntry.setJumlah(selectedAmount.get());
                    this.simpleBooleanProperty.set(!this.simpleBooleanProperty.get());
                    if (selectedAmount.get() == 0) {
                        this.temporaryBill.removeBarang(temporaryBillEntryIndex);
                        this.bills.remove(this.billCard);
                    }
                }
            });
            plusButton.setOnAction(event -> {
                if (selectedAmount.get() < this.barang.getStok()) {
                    if (this.temporaryBill == null) {
                        this.temporaryBillController.addTemporaryBill(new TemporaryBill(userId));
                        this.temporaryBill = this.temporaryBillController.getTemporaryBills().get(this.temporaryBillController.getTemporaryBillsLength() - 1);
                    }
                    if (selectedAmount.get() == 0) {
                        this.billCard = new BillCardComponent(selectedAmountString, barang).getComponent();
                        this.temporaryBillEntryIndex = this.temporaryBill.addBarang(barang, 1);
                        this.temporaryBillEntry = this.temporaryBill.getDaftar().get(temporaryBillEntryIndex);
                        this.bills.add(this.billCard);
                    } else {
                        this.temporaryBillEntry.setJumlah(selectedAmount.get() + 1);
                        this.simpleBooleanProperty.set(!this.simpleBooleanProperty.get());
                    }
                    selectedAmount.set(selectedAmount.get() + 1);
                }
            });

            addToChartActionContainer.setLeft(minusButton);
            addToChartActionContainer.setCenter(selectedAmountLabel);
            addToChartActionContainer.setRight(plusButton);
            addToChartActionContainer.getStyleClass().add("add-tochart-action-container-barang-card");

            this.componentRootLayout.setCenter(addToChartActionContainer);
        }

        Label priceLabel = new Label(barang.getHargaBarang().getNominal() + "");
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
