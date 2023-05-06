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
    private static File selectedFile;
    private final SplitPane pageRootLayout;
    private final Stage primaryStage;

    public SettingsPage(Stage primaryStage) {
        super(PageType.SettingsPage);
        this.pageRootLayout = new SplitPane();
        this.primaryStage = primaryStage;
        loadUI();
        setStyleSheet();
        this.tab.setContent(this.pageRootLayout);
    }

    public static void setFileValue(File file) {
        selectedFile = file;
    }

    private void loadUI() {
        // left pane
        VBox kiri = new VBox();

        Label bagianSettingLabel = new Label("Klasifikasi Setting");
        bagianSettingLabel.getStyleClass().add("setting-label");
        kiri.getChildren().addAll(bagianSettingLabel);

        // right pane
        GridPane barangContainer = new GridPane();
        barangContainer.getStyleClass().add("barang-container");

        Label labelSetting1 = new Label("Tempat penyimpanan file ");
        Label labelPenjelasanSetting1 = new Label("Lokasi dari file yang menyimpan data program.");
        TextField masukanSetting1 = new TextField();
        Button pilihFile = new Button("Pilih file");
        pilihFile.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Open Resource File");
                        File fileSaved = fileChooser.showOpenDialog(primaryStage);
                        SettingsPage.setFileValue(fileSaved);
                    }
                }
        );
        barangContainer.add(labelSetting1, 0, 0);
        barangContainer.add(labelPenjelasanSetting1, 0, 1);
        barangContainer.add(masukanSetting1, 0, 2);
        barangContainer.add(pilihFile, 0, 3);

        Label labelSetting2 = new Label("\nTipe penyimpanan file ");
        Label labelPenjelasanSetting2 = new Label("Tipe dari file yang menyimpan data program.");
        ChoiceBox categoryChoices = new ChoiceBox();
        categoryChoices.getItems().add("JSON");
        categoryChoices.getItems().add("XML");
        categoryChoices.getItems().add("OBJ");

        barangContainer.add(labelSetting2, 0, 4);
        barangContainer.add(labelPenjelasanSetting2, 0, 5);
        barangContainer.add(categoryChoices, 0, 6);


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
        settingScroll.setContent(barangContainer);
        VBox kanan = new VBox();
        kanan.getChildren().addAll(settingScroll);

        this.pageRootLayout.getItems().addAll(kiri, kanan);

    }

    private void setStyleSheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("css/settings-page.css");
    }
}
