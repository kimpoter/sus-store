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
import susstore.susstore.view.component.BarangEditCardComponent;
import susstore.susstore.models.Barang;
import susstore.susstore.Subscriber;
import susstore.susstore.controller.BarangController;
import susstore.susstore.controller.EditBarangController;
import javafx.stage.FileChooser;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AllBarangPage extends Page implements Subscriber {
    private final SplitPane pageRootLayout;
    private final Stage primaryStage;
    private BarangController barangController;
    private ScrollPane barangContainerScroll;
    private BorderPane formAndActionsContainer;
    private TextField namaBarangInput;
    private EditBarangController editBarangController;
    private TextField stockInput;
    private ChoiceBox<String> categoryChoices;
    private TextField hargaBarangInput;
    private TextField hargaBeliBarangInput;
    private Rectangle imageContainer;
    private String imagePath;

    public AllBarangPage(Stage primaryStage, BarangController barangController) {
        super(PageType.AllBarang);
        this.pageRootLayout = new SplitPane();
        this.primaryStage = primaryStage;
        this.barangController = barangController;
        this.editBarangController = new EditBarangController();
        loadUI();
        setStyleSheet();
        barangController.addSubscriber(this);
        editBarangController.addSubscriber(this);
        this.tab.setContent(this.pageRootLayout);
    }

    public void update(String s) {
        if(s=="choose-new-barang"){
            Image image = new Image(editBarangController.getBarang().getPathGambar(), false);
            imageContainer.setFill(new ImagePattern(image));
            this.namaBarangInput.setText(editBarangController.getBarang().getNama());
            this.stockInput.setText(editBarangController.getBarang().getStok()+"");
            this.categoryChoices.getSelectionModel().select(editBarangController.getBarang().getKategori());
            this.hargaBarangInput.setText(editBarangController.getBarang().getHargaJual() +"");
            this.hargaBeliBarangInput.setText(editBarangController.getBarang().getHargaBeli()+"");
            this.imagePath = editBarangController.getBarang().getPathGambar();
        }
        else{
        GridPane barangContainer = new GridPane();
        barangContainer.getStyleClass().add("barang-container");
        int index = 0;
        for (Barang b : barangController.getBarangs()) {
            BarangEditCardComponent barangCard = new BarangEditCardComponent(b,editBarangController);
            barangContainer.add(barangCard.getComponent(), index % 4, index / 4, 1, 1);
            index++;
        }
        barangContainerScroll.setContent(barangContainer);
        }
    }

    private void loadUI() {
        GridPane barangContainer = new GridPane();
        barangContainer.getStyleClass().add("barang-container");
        int index = 0;
        for (Barang b : barangController.getBarangs()) {
            BarangEditCardComponent barangCard = new BarangEditCardComponent(b,editBarangController);
            barangContainer.add(barangCard.getComponent(), index % 4, index / 4, 1, 1);
            index++;
        }

        barangContainerScroll = new ScrollPane();
        barangContainerScroll.setContent(barangContainer);
        barangContainerScroll.setFitToWidth(true);

        BorderPane allActionContainer = new BorderPane();

        // edit barang
        imageContainer = new Rectangle(0, 0, 180, 180);
        Image image = new Image("images/barang.jpg", false);
        imageContainer.setFill(new ImagePattern(image));
        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.setOnAction(
            e->{
            FileChooser fileChooser = new FileChooser();
            File fileSaved = fileChooser.showOpenDialog(primaryStage);
            if (fileSaved != null) {
                this.imagePath = fileSaved.getAbsolutePath();
                Image imagenew = new Image(imagePath, false);
                imageContainer.setFill(new ImagePattern(imagenew));
            }
        }
        );
        chooseImageButton.getStyleClass().add("choose-image-button");

        VBox imageAndChooseButtonContainer = new VBox();
        imageAndChooseButtonContainer.getStyleClass().add("image-choose-button-container");
        imageAndChooseButtonContainer.getChildren().addAll(imageContainer, chooseImageButton);

        Label namaBarangLabel = new Label("Nama Barang:");
        namaBarangInput = new TextField();
        VBox namaBarangContainer = new VBox();
        namaBarangLabel.getStyleClass().add("input-label-all-barang");
        namaBarangInput.getStyleClass().add("input-all-barang");
        namaBarangContainer.getChildren().addAll(namaBarangLabel, namaBarangInput);

        Label stockLabel = new Label("Stok:");
        stockInput = new TextField();
        VBox stockContainer = new VBox();
        stockLabel.getStyleClass().add("input-label-all-barang");
        stockInput.getStyleClass().add("input-all-barang");
        stockContainer.getStyleClass().add("stock-container-all-barang");
        stockContainer.getChildren().addAll(stockLabel, stockInput);

        Label categoryLabel = new Label("Kategori:");
        categoryChoices = new ChoiceBox();
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
        hargaBarangInput = new TextField();
        VBox hargaBarangContainer = new VBox();
        hargaBarangLabel.getStyleClass().add("input-label-all-barang");
        hargaBarangInput.getStyleClass().add("input-all-barang");
        hargaBarangContainer.getChildren().addAll(hargaBarangLabel, hargaBarangInput);

        Label hargaBeliBarangLabel = new Label("Harga Beli Barang:");
        hargaBeliBarangInput = new TextField();
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
        saveButton.setOnAction(
                e -> {
                    if(editBarangController.getBarang()!=null)
                    this.barangController.editBarang(editBarangController.getBarang().getID(),
                        namaBarangInput.getText(),
                        Integer.parseInt(stockInput.getText()),
                        "makanan",
                        imagePath,
                        Double.parseDouble(hargaBarangInput.getText()),
                        Double.parseDouble(hargaBeliBarangInput.getText())
                );

                }
        );
        HBox actionButtonsContainer = new HBox();
        cancelButton.getStyleClass().addAll("action-button-all-barang", "cancel-button-all-barang");
        saveButton.getStyleClass().addAll("action-button-all-barang", "save-button-all-barang");
        actionButtonsContainer.getStyleClass().add("action-buttons-container-all-barang");
        actionButtonsContainer.getChildren().addAll(cancelButton, saveButton);

        formAndActionsContainer = new BorderPane();
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
        this.pageRootLayout.getStylesheets().add("css/all-barang-page.css");
    }
}
