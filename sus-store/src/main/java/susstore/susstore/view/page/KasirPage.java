package susstore.susstore.view.page;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import susstore.susstore.Subscriber;
import susstore.susstore.controller.BarangController;
import susstore.susstore.controller.TemporaryBillController;
import susstore.susstore.models.Barang;
import susstore.susstore.models.TemporaryBill;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.BarangCardComponent;
import susstore.susstore.view.component.BillCardComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class KasirPage extends Page implements Subscriber {
    private final SplitPane pageRootLayout;
    private final TemporaryBillController temporaryBillController;
    private final GridPane barangContainer;
    private BarangController barangController;
    private List<TemporaryBill> temporaryBills;
    private VBox billElementsContainer;
    private ObservableList<Node> bills;
    private TemporaryBill temporaryBill;
    private ComboBox<String> customerInput;
    private Label totalPriceLabel;
    private SimpleBooleanProperty booleanProperty;

    public KasirPage(BarangController barangController, TemporaryBillController temporaryBillController) {
        super(PageType.Kasir);
        this.pageRootLayout = new SplitPane();
        this.barangController = barangController;
        this.barangContainer = new GridPane();
        this.temporaryBillController = temporaryBillController;
        this.billElementsContainer = new VBox();
        this.customerInput = new ComboBox<String>();
        this.totalPriceLabel = new Label();
        this.booleanProperty = new SimpleBooleanProperty();
        initializeBill();
        loadTemporaryBills();
        loadUI();
        loadBarang();
        setStyleSheet();
        barangController.addSubscriber(this);
        this.tab.setContent(this.pageRootLayout);
    }


    public void update(String s) {
        loadBarang();
    }

    private void loadTemporaryBills() {
        this.temporaryBills = this.temporaryBillController.getTemporaryBills();
    }

    private void loadTemporaryBill() {
        loadTemporaryBills();
        UUID userId;
        if (this.customerInput.getValue() == null || Objects.equals(this.customerInput.getValue(), "")) {
            userId = UUID.randomUUID();
        } else {
            userId = UUID.fromString(this.customerInput.getValue());
        }
        for (TemporaryBill temporaryBill : this.temporaryBills) {
            if (temporaryBill.getUserID() == userId) {
                this.temporaryBill = temporaryBill;
                System.out.println("AFJLJFLJSDLJFLKJFSKJFISHFISHFOISHH");
            }
        }
    }


    private void loadBarang() {
        int index = 0;
        UUID userId;
        loadTemporaryBills();
        if (this.customerInput.getValue() == null || Objects.equals(this.customerInput.getValue(), "")) {
            userId = null;
        } else {
            userId = UUID.fromString(this.customerInput.getValue());
        }
        if (userId != null) {
            this.billElementsContainer.getChildren().clear();
            System.out.println("USER ID:::::: " + userId);
            this.temporaryBill = null;
            for (TemporaryBill temporaryBill : this.temporaryBills) {
                if (temporaryBill.getUserID() == userId) {
                    this.temporaryBill = temporaryBill;
                }
            }
        }
        for (Barang barang : this.barangController.getBarangs()) {
            BarangCardComponent barangCard = new BarangCardComponent(userId, barang, this.bills, this.temporaryBillController, this.temporaryBill, this.booleanProperty);
            this.barangContainer.add(barangCard.getComponent(), index % 4, index / 4, 1, 1);
            index++;
        }
    }

    private void initializeBill() {
        this.bills = FXCollections.observableArrayList();
    }

    private void loadUI() {
        // left pane
        ScrollPane barangContainerScroll = new ScrollPane();
        barangContainerScroll.setContent(this.barangContainer);
        barangContainerScroll.setFitToWidth(true);

        ChoiceBox categoryChoices = new ChoiceBox();
        categoryChoices.getItems().add("Alat Tulis");
        categoryChoices.getItems().add("Perabotan");


        TextField searchInput = new TextField();
        Button searchButton = new Button("\uf002;");
        searchInput.setPromptText("Search...");
        searchInput.getStyleClass().add("input-kasir");
        searchButton.getStyleClass().add("search-button-kasir");
        HBox searchContainer = new HBox();
        searchContainer.getStyleClass().add("search-container-kasir");
        searchContainer.getChildren().addAll(searchInput, searchButton);

        BorderPane filterAndSearchContainer = new BorderPane();
        filterAndSearchContainer.setLeft(categoryChoices);
        filterAndSearchContainer.setRight(searchContainer);
        filterAndSearchContainer.getStyleClass().add("filter-search-container-kasir");

        BorderPane barangAndFiltersContainer = new BorderPane();
        barangAndFiltersContainer.setTop(filterAndSearchContainer);
        barangAndFiltersContainer.setCenter(barangContainerScroll);

        // right container
        customerInput.setEditable(true);
        customerInput.setPromptText("Choose Customer");
        customerInput.getStyleClass().add("customer-input-kasir");
        customerInput.getItems().addAll("guya", "akane", "kana", "jihu", "kazuha");
        for (int i = 0; i < 50; i++) {
            customerInput.getItems().add(Integer.toString(i));
        }
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            loadBarang();
            if (this.temporaryBill == null) {
                this.totalPriceLabel.setText("");
            } else {
                this.totalPriceLabel.setText("" + this.temporaryBill.getBillTotal());
            }
        });
        HBox customerAndOkContainer = new HBox();
        customerAndOkContainer.setSpacing(10);
        customerAndOkContainer.setAlignment(Pos.CENTER);
        customerAndOkContainer.getChildren().addAll(customerInput, okButton);

        TextField pointInput = new TextField();
        pointInput.setPromptText("Point");

        BorderPane customerAndPointContainer = new BorderPane();
        Insets customerAndPointInsets = new Insets(10, 20, 20, 20);
        BorderPane.setMargin(customerAndOkContainer, customerAndPointInsets);
        BorderPane.setMargin(pointInput, customerAndPointInsets);
        BorderPane.setAlignment(customerAndPointContainer, Pos.CENTER);
        BorderPane.setAlignment(pointInput, Pos.CENTER);
        customerAndPointContainer.setLeft(customerAndOkContainer);
        customerAndPointContainer.setRight(pointInput);

        Label billLabel = new Label("Current Bill:");
        billLabel.getStyleClass().addAll("label-kasir", "bill-label-kasir");

        this.bills.addListener((ListChangeListener<? super Node>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Node bill : c.getAddedSubList()) {
                        this.billElementsContainer.getChildren().add(bill);
                    }
                    loadTemporaryBill();
                    System.out.println("TEMPORARYBILLDAFTAR:::" + this.temporaryBill.getDaftarEntry().size());
                    this.totalPriceLabel.setText("" + this.temporaryBill.getBillTotal());
                }
                if (c.wasRemoved()) {
                    for (Node bill : c.getRemoved()) {
                        this.billElementsContainer.getChildren().remove(bill);
                    }
                    loadTemporaryBill();
                    this.totalPriceLabel.setText("" + this.temporaryBill.getBillTotal());
                }
            }
        });
        this.billElementsContainer.getStyleClass().add("bill-elements-container-kasir");

        this.booleanProperty.addListener(((observable, oldValue, newValue) -> {
            loadTemporaryBill();
            this.totalPriceLabel.setText("" + this.temporaryBill.getBillTotal());
        }));

        ScrollPane billScroll = new ScrollPane();
        billScroll.setContent(this.billElementsContainer);
        billScroll.setFitToWidth(true);
        Label totalLabel = new Label("Total:");
        BorderPane totalContainer = new BorderPane();
        totalLabel.getStyleClass().add("label-kasir");
        totalPriceLabel.getStyleClass().add("label-kasir");
        totalContainer.getStyleClass().add("total-container-kasir");
        totalContainer.setLeft(totalLabel);
        totalContainer.setRight(totalPriceLabel);
        VBox billContainer = new VBox();
        billContainer.getStyleClass().add("bill-container-kasir");
        billContainer.getChildren().addAll(billLabel, billScroll, totalContainer);


        Button cancelButton = new Button("Cancel");
        Button checkoutButton = new Button("Checkout");
        HBox actionButtonsContainer = new HBox();
        cancelButton.getStyleClass().addAll("action-button-kasir", "cancel-button-kasir");
        checkoutButton.getStyleClass().addAll("action-button-kasir", "checkout-button-kasir");
        actionButtonsContainer.getStyleClass().add("action-buttons-container-kasir");
        actionButtonsContainer.getChildren().addAll(cancelButton, checkoutButton);

        BorderPane allActionContainer = new BorderPane();
        BorderPane.setAlignment(customerAndPointContainer, Pos.CENTER);
        allActionContainer.setTop(customerAndPointContainer);
        allActionContainer.setCenter(billContainer);
        allActionContainer.setBottom(actionButtonsContainer);
        allActionContainer.getStyleClass().add("all-action-container-kasir");

        this.pageRootLayout.setDividerPositions(0.65, 0.35);
        this.pageRootLayout.getItems().addAll(barangAndFiltersContainer, allActionContainer);
    }

    private void setStyleSheet() {
        this.barangContainer.getStyleClass().add("barang-container");
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("css/kasir-page.css");
    }
}
