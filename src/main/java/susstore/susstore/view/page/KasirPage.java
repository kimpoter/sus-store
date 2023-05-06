package susstore.susstore.view.page;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import susstore.susstore.models.Barang;
import susstore.susstore.models.Bill;
import susstore.susstore.models.TemporaryBill;
import susstore.susstore.view.BarangCardType;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.BarangCardComponent;
import susstore.susstore.view.component.BillCardComponent;

import java.util.HashMap;

public class KasirPage extends Page {
    private final SplitPane pageRootLayout;
    private final ObservableList<Node> bills;
    private String selectedUser;
    private TemporaryBill temporaryBill;

    public KasirPage(ObservableList<Node> bills) {
        super(PageType.Kasir);
        this.pageRootLayout = new SplitPane();
        this.bills = bills;
        loadUI();
        setStyleSheet();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        // left pane
        GridPane barangContainer = new GridPane();
        barangContainer.getStyleClass().add("barang-container");
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 4; row++) {
                BarangCardComponent barangCard = new BarangCardComponent(BarangCardType.Kasir, this.bills);
                barangContainer.add(barangCard.getComponent(), row, col, 1, 1);
            }
        }
        ScrollPane barangContainerScroll = new ScrollPane();
        barangContainerScroll.setContent(barangContainer);
        barangContainerScroll.setFitToWidth(true);

        ChoiceBox<String> categoryChoices = new ChoiceBox<>();
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
        ComboBox<String> customerInput = new ComboBox<>();
        customerInput.setEditable(true);
        customerInput.setPromptText("Choose Customer");
        customerInput.getStyleClass().add("customer-input-kasir");
        customerInput.getItems().addAll("guya", "akane", "kana", "jihu", "kazuha");
        for (int i = 0; i < 50; i++) {
            customerInput.getItems().add(Integer.toString(i));
        }
        Button okButton = new Button("OK");
        HBox customerAndOkContainer = new HBox();
        customerAndOkContainer.setSpacing(10);
        customerAndOkContainer.setAlignment(Pos.CENTER);
        customerAndOkContainer.getChildren().addAll(customerInput, okButton);

        okButton.setOnAction(e -> {
            this.selectedUser = customerInput.getSelectionModel().getSelectedItem();
        });

        TextField pointInput = new TextField();
        pointInput.setPromptText("Point");
        pointInput.getStyleClass().add("input-kasir");

        BorderPane customerAndPointContainer = new BorderPane();
        Insets customerAndPointContainerSpacing = new Insets(0, 20, 0, 20);
        BorderPane.setMargin(customerAndOkContainer, customerAndPointContainerSpacing);
        BorderPane.setMargin(pointInput, customerAndPointContainerSpacing);
        BorderPane.setAlignment(customerAndOkContainer, Pos.CENTER);
        BorderPane.setAlignment(pointInput, Pos.CENTER);
        customerAndPointContainer.setLeft(customerAndOkContainer);
        customerAndPointContainer.setRight(pointInput);

        Label billLabel = new Label("Current Bill:");
        billLabel.getStyleClass().addAll("label-kasir", "bill-label-kasir");
        VBox billElementsContainer = new VBox();
        this.bills.addListener((ListChangeListener<? super Node>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Node item : c.getAddedSubList()) {
                        billElementsContainer.getChildren().add(item);
                    }
                }
            }
        });
        billElementsContainer.getChildren().addAll(this.bills);
        for (int i = 0; i < 100; i++) {

//            billElementsContainer.getChildren().add(new BillCardComponent().getComponent());
        }
        billElementsContainer.getStyleClass().add("bill-elements-container-kasir");

        ScrollPane billScroll = new ScrollPane();
        billScroll.setContent(billElementsContainer);
        billScroll.setFitToWidth(true);
        Label totalLabel = new Label("Total:");
        Label totalPriceLabel = new Label("1000000");
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
        BorderPane.setAlignment(customerInput, Pos.CENTER);
        allActionContainer.setTop(customerAndPointContainer);
        allActionContainer.setCenter(billContainer);
        allActionContainer.setBottom(actionButtonsContainer);
        allActionContainer.getStyleClass().add("all-action-container-kasir");

        this.pageRootLayout.setDividerPositions(0.65, 0.35);
        this.pageRootLayout.getItems().addAll(barangAndFiltersContainer, allActionContainer);
    }

    private void setStyleSheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("css/kasir-page.css");
    }
}
