package susstore.susstore.view.page;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import susstore.susstore.Subscriber;
import susstore.susstore.controller.FixedBillController;
import susstore.susstore.controller.UserController;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.CustomerTransactionCardComponent;

public class TransactionHisotryPage extends Page {
    private final SplitPane pageRootLayout;
    private FixedBillController fixedBillContainer;
    private UserController userController;

    public TransactionHisotryPage(FixedBillController fixedBillController, UserController userController) {
        super(PageType.TransactionHistory);
        this.pageRootLayout = new SplitPane();
        this.fixedBillContainer = fixedBillController;
        loadUI();
        setStyleSheet();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        VBox leftPane = new VBox();
        for (int i = 0; i < 50; i++) {
            leftPane.getChildren().add(new CustomerTransactionCardComponent().getComponent());
        }
        leftPane.getStyleClass().add("left-pane-transaction-history");
        ScrollPane leftScroll = new ScrollPane();
        leftScroll.setContent(leftPane);
        leftScroll.setFitToWidth(true);

        VBox rightPane = new VBox();
        for (int i = 0; i < 10; i++) {

        }

        this.pageRootLayout.getItems().addAll(leftScroll, rightPane);
    }

    private void setStyleSheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("css/transaction-history-page.css");
    }
}
