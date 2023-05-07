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
import susstore.susstore.models.Barang;
import susstore.susstore.Subscriber;
import susstore.susstore.controller.BarangController;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import susstore.susstore.models.Nominal;

public class NewBarangPage extends Page{
    private final VBox pageRootLayout;
    private final Stage primaryStage;
    private String imagePath;
    private TextField stockInput;
    private TextField hargaBarangInput;
    private TextField namaBarangInput;
    private TextField hargaBeliBarangInput;
    private ChoiceBox categoryChoices;
    private BarangController barangController;
    private ScrollPane barangContainerScroll;
    private Rectangle imageContainer;

    public NewBarangPage(Stage primaryStage,BarangController barangController) {
        super(PageType.AllBarang);
        this.pageRootLayout = new VBox();
        this.primaryStage = primaryStage;
        this.barangController = barangController;
        loadUI();
        setStyleSheet();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        // barang
        GridPane barangContainer = new GridPane();
        imagePath="images/default.jpg";
        barangContainer.getStyleClass().add("barang-container");
        int index=0;
        for(Barang b : barangController.getBarangs()){
            BarangCardComponent barangCard = new BarangCardComponent(b);
            barangContainer.add(barangCard.getComponent(), index%4, index/4, 1, 1);
            index++;
        }

        barangContainerScroll = new ScrollPane();
        barangContainerScroll.setContent(barangContainer);
        barangContainerScroll.setFitToWidth(true);

        BorderPane allActionContainer = new BorderPane();

        // edit barang
        imageContainer = new Rectangle(0, 0, 180, 180);
        Image image = new Image(imagePath, false);
        imageContainer.setFill(new ImagePattern(image));
        Button chooseImageButton = new Button("Choose Image");
        
        chooseImageButton.getStyleClass().add("choose-image-button");
        chooseImageButton.setOnAction(
            e -> {
                FileChooser fileChooser = new FileChooser();
                File fileSaved = fileChooser.showOpenDialog(primaryStage);  
                this.imagePath = fileSaved.getAbsolutePath();
                Image imagenew = new Image(imagePath, false);
                imageContainer.setFill(new ImagePattern(imagenew));
            }
        );

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
        stockInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    stockInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
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
        hargaBarangInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*\\.?\\d+") || !newValue.matches("\\d*\\.?") ){
                    hargaBarangInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        VBox hargaBarangContainer = new VBox();
        hargaBarangLabel.getStyleClass().add("input-label-all-barang");
        hargaBarangInput.getStyleClass().add("input-all-barang");
        hargaBarangContainer.getChildren().addAll(hargaBarangLabel, hargaBarangInput);

        Label hargaBeliBarangLabel = new Label("Harga Beli Barang:");
        hargaBeliBarangInput = new TextField();
        hargaBeliBarangInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*\\.?\\d+") || !newValue.matches("\\d*\\.?")) {
                    hargaBeliBarangInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
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
        saveButton.setOnAction(
            e->this.barangController.addBarang(new Barang(namaBarangInput.getText(), 
            Integer.parseInt(stockInput.getText()), 
            "makanan", 
            imagePath, 
            new Nominal("IDR",Double.parseDouble(hargaBarangInput.getText())), 
            new Nominal("IDR",Double.parseDouble(hargaBeliBarangInput.getText()))
            ))
        );
        actionButtonsContainer.getStyleClass().add("action-buttons-container-all-barang");
        actionButtonsContainer.getChildren().addAll(cancelButton, saveButton);

        BorderPane formAndActionsContainer = new BorderPane();
        formAndActionsContainer.getStyleClass().add("form-actions-container");
        formAndActionsContainer.setCenter(imageAndInputContainer);
        formAndActionsContainer.setBottom(actionButtonsContainer);
//        formAndActionsContainer.setCenter();
//        formAndActionsContainer.getChildren().addAll(imageAndChooseButtonContainer, inputContainer, actionButtonsContainer);

        this.pageRootLayout.getChildren().addAll(formAndActionsContainer);
    }

    private void setStyleSheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("css/all-barang-page.css");
    }
}
