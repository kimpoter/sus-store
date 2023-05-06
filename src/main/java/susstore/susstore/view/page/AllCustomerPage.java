package susstore.susstore.view.page;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.CustomerCardComponent;

public class AllCustomerPage extends Page {
    private final VBox pageRootLayout;
    private final ScrollPane scrollPane;

    public AllCustomerPage() {
        super(PageType.AllCustomerPage);
        this.pageRootLayout = new VBox();
        this.scrollPane = new ScrollPane();
        loadUI();
        setStylesheet();
        this.tab.setContent(this.scrollPane);
    }

    private void loadUI() {
        for (int i = 0; i < 20; i++) {
            CustomerCardComponent card = new CustomerCardComponent();
            this.pageRootLayout.getChildren().add(card.getComponent());
        }
        this.scrollPane.setContent(this.pageRootLayout);
        this.scrollPane.setFitToWidth(true);
    }


    private void setStylesheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.scrollPane.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/all-customer-page.css");
    }
}
