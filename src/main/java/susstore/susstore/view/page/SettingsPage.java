package susstore.susstore.view.page;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.BarangCardComponent;
import javafx.stage.FileChooser;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class SettingsPage extends Page {
    private final SplitPane pageRootLayout;
    private final Stage primaryStage;
    private Label filePath;
    private File selectedFile;
    private GridPane settingContainer;

    public SettingsPage(Stage primaryStage) {
        super(PageType.SettingsPage);
        this.pageRootLayout = new SplitPane();
        this.primaryStage = primaryStage;
        loadUI();
        setStyleSheet();
        this.tab.setContent(this.pageRootLayout);
    }

    public void addNewSettings(String s1, String s2){
        Label labelSetting1 = new Label(s1);
        Label labelPenjelasanSetting1 = new Label(s2);
        this.settingContainer.add(labelSetting1, 0, 7);
        this.settingContainer.add(labelPenjelasanSetting1, 0, 8);
    }

    private void loadUI() {
        // left pane
        VBox kiri = new VBox();

        Label bagianSettingLabel = new Label("Klasifikasi Setting");
        bagianSettingLabel.getStyleClass().add("setting-label");
        kiri.getChildren().addAll(bagianSettingLabel);

        // right pane

        settingContainer = new GridPane();
        settingContainer.getStyleClass().add("barang-container");

        Label labelSetting1 = new Label("Tempat penyimpanan file ");
        Label labelPenjelasanSetting1 = new Label("Lokasi dari file yang menyimpan data program.");
        filePath = new Label("Belum ada file yang terpilih");
        Button pilihFile = new Button("Pilih file");

        pilihFile.setOnAction(
            e -> {
                FileChooser fileChooser = new FileChooser();
                File fileSaved = fileChooser.showOpenDialog(primaryStage);  
                this.selectedFile = fileSaved;
                this.filePath.setText("File yang dipilih adalah : " + selectedFile.getAbsolutePath());
            }
        );
        settingContainer.add(labelSetting1, 0, 0);
        settingContainer.add(labelPenjelasanSetting1, 0, 1);
        settingContainer.add(filePath, 0, 2);
        settingContainer.add(pilihFile, 0, 3);

        Label labelSetting2 = new Label("\nTipe penyimpanan file ");
        Label labelPenjelasanSetting2 = new Label("Tipe dari file yang menyimpan data program.");
        ChoiceBox categoryChoices = new ChoiceBox();
        categoryChoices.getItems().add("JSON");
        categoryChoices.getItems().add("XML");
        categoryChoices.getItems().add("OBJ");

        settingContainer.add(labelSetting2, 0, 4);
        settingContainer.add(labelPenjelasanSetting2, 0, 5);
        settingContainer.add(categoryChoices, 0, 6);


        /*
        for (int row = 0; row < 0; row++) {
            Label labelSetting = new Label("Setting ke- " + row);
            Label labelPenjelasanSetting = new Label("Penjelasan penggantian dari setting ke-" + row);
            TextField masukanSetting = new TextField();
            barangContainer.add(labelSetting, 0, 3*row);
            barangContainer.add(labelPenjelasanSetting, 0, 3*row+1);
            barangContainer.add(masukanSetting, 0, 3*row+2);
        }
        */

        ScrollPane settingScroll = new ScrollPane();
        settingScroll.setContent(settingContainer);
        VBox kanan = new VBox();
        kanan.getChildren().addAll(settingScroll);

        this.pageRootLayout.getItems().addAll(kiri,kanan);
    }

    private void setStyleSheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("css/settings-page.css");
    }
}