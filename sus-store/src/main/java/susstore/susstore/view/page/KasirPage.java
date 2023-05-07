package susstore.susstore.view.page;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
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
import susstore.susstore.controller.FixedBillController;
import susstore.susstore.controller.TemporaryBillController;
import susstore.susstore.controller.UserController;
import susstore.susstore.models.*;
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
    private UserController userController;
    private FixedBillController fixedBillController;

    public KasirPage(BarangController barangController, TemporaryBillController temporaryBillController, UserController userController, FixedBillController fixedBillController) {
        super(PageType.Kasir);
        this.pageRootLayout = new SplitPane();
        this.barangController = barangController;
        this.barangContainer = new GridPane();
        this.temporaryBillController = temporaryBillController;
        this.billElementsContainer = new VBox();
        this.customerInput = new ComboBox<String>();
        this.totalPriceLabel = new Label();
        this.booleanProperty = new SimpleBooleanProperty();
        this.userController = userController;
        this.fixedBillController = fixedBillController;
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

    private UUID loadTemporaryBill() {
        loadTemporaryBills();
        UUID userId;
        if (this.customerInput.getValue() == null || Objects.equals(this.customerInput.getValue(), "")) {
            userId = UUID.fromString(this.userController.addCustomer(new Customer()));
            this.customerInput.getItems().add(userId.toString());
            this.customerInput.setValue(userId.toString());
        } else {
            userId = UUID.fromString(this.customerInput.getValue());
        }

        this.temporaryBill = null;
        for (TemporaryBill temporaryBill : this.temporaryBills) {
            if (temporaryBill.getUserID().equals(userId)) {
                System.out.println("DALEM::" + userId);
                this.temporaryBill = temporaryBill;
            }
        }
        return userId;
    }


    private void loadBarang() {
        int index = 0;
        UUID userId = loadTemporaryBill();
        System.out.println("FIXED BILL SIZE:" + this.fixedBillController.getFixedBills().size());
        System.out.println("TEMP BILL SIZE:" + this.temporaryBillController.getTemporaryBills().size());
//        this.billElementsContainer.getChildren().clear();
//        this.temporaryBill = null;
//        for (TemporaryBill temporaryBill : this.temporaryBills) {
//            if (temporaryBill.getUserID() == userId) {
//                this.temporaryBill = temporaryBill;
//            }
//        }

        System.out.println("INSIDE LOAD BARANG");
        this.bills.clear();
        this.billElementsContainer.getChildren().clear();

        System.out.println(userId);

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
        customerInput.getItems().add("");
        this.customerInput.setStyle("-fx-max-width: 200px;");
        for (TemporaryBill temporaryBill1 : temporaryBills) {
            for (Customer customer : userController.getCustomers()) {
                if (customer.getUserID().equals(temporaryBill1.getUserID())) {
                    customerInput.getItems().add(temporaryBill1.getUserID().toString());
                }
            }
        }

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            loadBarang();
            this.totalPriceLabel.setText("");
            if (this.temporaryBill != null) {
                this.totalPriceLabel.setText("" + this.temporaryBill.getBillTotal());
            }
        });
        HBox customerAndOkContainer = new HBox();
        customerAndOkContainer.setSpacing(10);
        customerAndOkContainer.setAlignment(Pos.CENTER);
        customerAndOkContainer.getChildren().addAll(customerInput, okButton);

        TextField pointInput = new TextField();
        pointInput.setPromptText("Point");
        pointInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*\\.?\\d+") || !newValue.matches("\\d*\\.?")) {
                    pointInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

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
                    if (this.temporaryBill != null) {
                        this.totalPriceLabel.setText("" + this.temporaryBill.getBillTotal());
                    } else {
                        this.totalPriceLabel.setText("");
                    }
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
        checkoutButton.setOnAction(e -> {
            this.fixedBillController.addFixedBill(new FixedBill(this.temporaryBill));
            int indexFound = 0;
            for (TemporaryBill temporaryBill1 : this.temporaryBillController.getTemporaryBills()) {
                if (temporaryBill1.getID() == this.temporaryBill.getID()) {
                    indexFound = 0;
                    for (TemporaryBillEntry temporaryBillEntry : temporaryBill1.getDaftarEntry()) {
                        temporaryBillEntry.getProduct().setStok(temporaryBillEntry.getProduct().getStok() - temporaryBillEntry.getJumlah());
                    }
                }
            }
            this.temporaryBillController.getTemporaryBills().remove(indexFound);
            loadBarang();
        });
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
