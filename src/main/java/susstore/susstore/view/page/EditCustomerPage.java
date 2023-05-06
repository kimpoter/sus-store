package susstore.susstore.view.page;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.JoinDataTest;

public class EditCustomerPage extends Page {
    private final BorderPane pageRootLayout;
    private final JoinDataTest joinDataTest;

    public EditCustomerPage(JoinDataTest joinDataTest) {
        super(PageType.EditCustomerPage);
        this.pageRootLayout = new BorderPane();
        this.joinDataTest = joinDataTest;
        loadUI();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        Label nameLabel = new Label();
        nameLabel.setStyle("-fx-text-fill: white;");
        nameLabel.textProperty().bind(this.joinDataTest.nameProperty());
        this.pageRootLayout.setCenter(nameLabel);
    }
}
