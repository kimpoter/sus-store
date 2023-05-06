package susstore.susstore.view.page;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.BarangCardComponent;

public class KasirPage extends Page {
    private final SplitPane pageRootLayout;

    public KasirPage() {
        super(PageType.Kasir);
        this.pageRootLayout = new SplitPane();
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
                BarangCardComponent barangCard = new BarangCardComponent();
                barangContainer.add(barangCard.getComponent(), row, col, 1, 1);
            }
        }
        ScrollPane barangContainerScroll = new ScrollPane();
        barangContainerScroll.setContent(barangContainer);
        barangContainerScroll.setFitToWidth(true);

        ChoiceBox categoryChoices = new ChoiceBox();
        categoryChoices.getItems().add("Alat Tulis");
        categoryChoices.getItems().add("Perabotan");

        TextField searchInput = new TextField();
        Button searchButton = new Button("Search");
        searchInput.setPromptText("Search...");
        HBox searchContainer = new HBox();
        searchContainer.getChildren().addAll(searchInput, searchButton);

        BorderPane filterAndSearchContainer = new BorderPane();
        filterAndSearchContainer.setLeft(categoryChoices);
        filterAndSearchContainer.setRight(searchContainer);

        BorderPane barangAndFiltersContainer = new BorderPane();
        barangAndFiltersContainer.setTop(filterAndSearchContainer);
        barangAndFiltersContainer.setCenter(barangContainerScroll);


        // right pane
        BorderPane allActionContainer = new BorderPane();

        this.pageRootLayout.getItems().addAll(barangAndFiltersContainer, allActionContainer);
    }

    private void setStyleSheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/kasir-page.css");
    }
}
