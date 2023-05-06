package susstore.susstore.view.page;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.CustomerCardComponent;

public class AllCustomerPage extends Page {
    private final SplitPane pageRootLayout;

    public AllCustomerPage() {
        super(PageType.AllCustomerPage);
        this.pageRootLayout = new SplitPane();
        loadUI();
        setStylesheet();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        VBox customersContainer = new VBox();
        for (int i = 0; i < 20; i++) {
            CustomerCardComponent card = new CustomerCardComponent();
            customersContainer.getChildren().add(card.getComponent());
        }
        customersContainer.getStyleClass().add("customers-container-all-customer");

        ScrollPane customersContainerScroll = new ScrollPane();
        customersContainerScroll.setContent(customersContainer);
        customersContainerScroll.setFitToWidth(true);

        Label nameLabel = new Label("Name:");
        TextField nameInput = new TextField();
        nameLabel.getStyleClass().add("input-label-all-customer");
        nameInput.getStyleClass().add("input-all-customer");

        Label membershipLabel = new Label("Membership:");
        ChoiceBox membershipChoices = new ChoiceBox();
        membershipChoices.getItems().add("VIP");
        membershipChoices.getItems().add("MEMBER");
        VBox membershipInputContainer = new VBox();
        membershipLabel.getStyleClass().add("input-label");
        membershipChoices.getSelectionModel().selectFirst();
        membershipInputContainer.getChildren().addAll(membershipLabel, membershipChoices);

        Label statusLabel = new Label("Status:");
        ChoiceBox statusChoices = new ChoiceBox();
        statusChoices.getItems().add("ACTIVE");
        statusChoices.getItems().add("DISABLED");
        VBox statusInputContainer = new VBox();
        statusLabel.getStyleClass().add("input-label");
        statusChoices.getSelectionModel().selectFirst();
        statusInputContainer.getChildren().addAll(statusLabel, statusChoices);


        BorderPane formAndActionsContainer = new BorderPane();


        this.pageRootLayout.setDividerPositions(0.65, 0.35);
        this.pageRootLayout.getItems().addAll(customersContainerScroll, formAndActionsContainer);
    }


    private void setStylesheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/all-customer-page.css");
    }
}
