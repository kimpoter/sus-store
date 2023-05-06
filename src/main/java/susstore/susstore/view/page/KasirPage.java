package susstore.susstore.view.page;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.BarangCardComponent;

public class KasirPage extends Page {
    private final SplitPane pageRootLayout;
    private final ScrollPane scrollPane;

    public KasirPage() {
        super(PageType.Kasir);
        this.pageRootLayout = new SplitPane();
        this.scrollPane = new ScrollPane();
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
        this.scrollPane.setContent(barangContainer);
        this.scrollPane.setFitToWidth(true);

        // right pane
        BorderPane allActionContainer = new BorderPane();

        this.pageRootLayout.getItems().addAll(this.scrollPane, allActionContainer);
    }

    private void setStyleSheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/kasir-page.css");
    }
}
