package susstore.susstore.view.page;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.BarangCardComponent;

public class AllBarangPage extends Page {
    private final SplitPane pageRootLayout;
    private final Stage primaryStage;

    public AllBarangPage(Stage primaryStage) {
        super(PageType.AllBarang);
        this.pageRootLayout = new SplitPane();
        this.primaryStage = primaryStage;
        loadUI();
        setStyleSheet();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        // barang
        GridPane barangContainer = new GridPane();
        barangContainer.getStyleClass().add("barang-container");
        for (int col = 0; col < 10; col++) {
            for (int row = 0; row < 4; row++) {
                BarangCardComponent barangCard = new BarangCardComponent();
                barangContainer.add(barangCard.getComponent(), row, col, 1, 1);
            }
        }

        ScrollPane barangContainerScroll = new ScrollPane();
        barangContainerScroll.setContent(barangContainer);
        barangContainerScroll.setFitToWidth(true);

        BorderPane allActionContainer = new BorderPane();

        // edit barang
        Rectangle imageContainer = new Rectangle(0, 0, 180, 180);
        Image image = new Image("/src/main/java/susstore/susstore/assets/barang.jpg", false);
        imageContainer.setFill(new ImagePattern(image));
        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.getStyleClass().add("choose-image-button");

        VBox imageAndChooseButtonContainer = new VBox();
        imageAndChooseButtonContainer.getStyleClass().add("image-choose-button-container");
        imageAndChooseButtonContainer.getChildren().addAll(imageContainer, chooseImageButton);

        Label namaBarangLabel = new Label("Nama Barang:");
        TextField namaBarangInput = new TextField();
        VBox namaBarangContainer = new VBox();
        namaBarangLabel.getStyleClass().add("input-label-all-barang");
        namaBarangInput.getStyleClass().add("input-all-barang");
        namaBarangContainer.getChildren().addAll(namaBarangLabel, namaBarangInput);

        Label stockLabel = new Label("Stok:");
        TextField stockInput = new TextField();
        VBox stockContainer = new VBox();
        stockLabel.getStyleClass().add("input-label-all-barang");
        stockInput.getStyleClass().add("input-all-barang");
        stockContainer.getStyleClass().add("stock-container-all-barang");
        stockContainer.getChildren().addAll(stockLabel, stockInput);

        Label categoryLabel = new Label("Kategori:");
        ChoiceBox categoryChoices = new ChoiceBox();
        categoryChoices.getItems().add("Peralatan Rumah");
        categoryChoices.getItems().add("Alat Tulis");
        VBox categoryContainer = new VBox();
        categoryLabel.getStyleClass().add("input-label-all-barang");
        categoryChoices.getStyleClass().add("choices-all-barang");
        categoryContainer.getStyleClass().add("category-container-all-barang");
        categoryContainer.getChildren().addAll(categoryLabel, categoryChoices);


        BorderPane stockCategoryContainer = new BorderPane();
        stockCategoryContainer.setLeft(stockContainer);
        stockCategoryContainer.setRight(categoryContainer);

        Label hargaBarangLabel = new Label("Harga Barang:");
        TextField hargaBarangInput = new TextField();
        VBox hargaBarangContainer = new VBox();
        hargaBarangLabel.getStyleClass().add("input-label-all-barang");
        hargaBarangInput.getStyleClass().add("input-all-barang");
        hargaBarangContainer.getChildren().addAll(hargaBarangLabel, hargaBarangInput);

        Label hargaBeliBarangLabel = new Label("Harga Beli Barang:");
        TextField hargaBeliBarangInput = new TextField();
        VBox hargaBeliBarangContainer = new VBox();
        hargaBeliBarangLabel.getStyleClass().add("input-label-all-barang");
        hargaBeliBarangInput.getStyleClass().add("input-all-barang");
        hargaBeliBarangContainer.getChildren().addAll(hargaBeliBarangLabel, hargaBeliBarangInput);

        VBox inputContainer = new VBox();
        inputContainer.getChildren().addAll(namaBarangContainer, stockCategoryContainer, hargaBarangContainer, hargaBeliBarangContainer);
        inputContainer.getStyleClass().addAll("input-container-all-barang");

        VBox imageAndInputContainer = new VBox();
        imageAndInputContainer.getStyleClass().add("image-input-container-all-barang");
        imageAndInputContainer.getChildren().addAll(imageAndChooseButtonContainer, inputContainer);

        Button cancelButton = new Button("Cancel");
        Button saveButton = new Button("Save");
        HBox actionButtonsContainer = new HBox();
        cancelButton.getStyleClass().addAll("action-button-all-barang", "cancel-button-all-barang");
        saveButton.getStyleClass().addAll("action-button-all-barang", "save-button-all-barang");
        actionButtonsContainer.getStyleClass().add("action-buttons-container-all-barang");
        actionButtonsContainer.getChildren().addAll(cancelButton, saveButton);

        BorderPane formAndActionsContainer = new BorderPane();
        formAndActionsContainer.getStyleClass().add("form-actions-container");
        formAndActionsContainer.setCenter(imageAndInputContainer);
        formAndActionsContainer.setBottom(actionButtonsContainer);
//        formAndActionsContainer.setCenter();
//        formAndActionsContainer.getChildren().addAll(imageAndChooseButtonContainer, inputContainer, actionButtonsContainer);

        this.pageRootLayout.setDividerPositions(0.65, 0.35);
        this.pageRootLayout.getItems().addAll(barangContainerScroll, formAndActionsContainer);
    }

    private void setStyleSheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/all-barang-page.css");
    }
}
