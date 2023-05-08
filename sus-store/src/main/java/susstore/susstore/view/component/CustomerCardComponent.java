package susstore.susstore.view.component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;
import susstore.susstore.controller.UpdateCustomerController;


public class CustomerCardComponent {
    private final BorderPane componentRootLayout;
    private final VBox idAndMembershipContainer;
    private final HBox statusAndActionsContainer;
    private final VBox nameAndPhoneContainer;
    private Customer containedCustomer;
    private Member memberInfo;
    private UpdateCustomerController updateCustomerController;

    public CustomerCardComponent(Customer c,UpdateCustomerController updateCustomerController) {
        this.componentRootLayout = new BorderPane();
        this.idAndMembershipContainer = new VBox();
        this.statusAndActionsContainer = new HBox();
        this.nameAndPhoneContainer = new VBox();
        this.containedCustomer = c;
        this.updateCustomerController = updateCustomerController;
        loadUI();
    }

    private void loadUI() {
        // left
        // Label membershipLabel = new Label(memberInfo==null?"Tidak terdaftar":
        // memberInfo.getMembership());
        // membershipLabel.getStyleClass().add("membership-label");

        // center
        Label nameLabel = new Label(containedCustomer.getUserID() + "");
        nameLabel.getStyleClass().add("name-label");

        this.nameAndPhoneContainer.getChildren().addAll(nameLabel);

        // right
        Button deleteButton = new Button("\uf2ed;");
        Button editButton = new Button("\uf4ff;");
        deleteButton.getStyleClass().add("delete-action-button");
        editButton.getStyleClass().add("edit-action-button");

        editButton.setOnAction(
            e->{
                updateCustomerController.setChoosenCustomer(containedCustomer);
            }

        );
        Label statusLabel = new Label("ACTIVE");
        Label transactionLabel = new Label(containedCustomer.getJumlahTransaksi() + " transactions");
        statusLabel.getStyleClass().addAll("status-label", "status-label-active");
        transactionLabel.getStyleClass().add("transaction-label");

        this.statusAndActionsContainer.getChildren().addAll(editButton, deleteButton);

        // root layout
        this.componentRootLayout.setRight(this.statusAndActionsContainer);
        this.componentRootLayout.setCenter(this.nameAndPhoneContainer);

        // stylesheet
        setStyleSheet();
    }

    private void setStyleSheet() {
        this.idAndMembershipContainer.getStyleClass().add("id-membership-container");
        this.statusAndActionsContainer.getStyleClass().add("status-actions-container");
        this.nameAndPhoneContainer.getStyleClass().add("name-phone-container");
        this.componentRootLayout.getStyleClass().add("root-component-layout");
        this.componentRootLayout.getStylesheets().add("css/customer-card-component.css");
    }

    public Pane getComponent() {
        return this.componentRootLayout;
    }
}
