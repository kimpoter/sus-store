package susstore.susstore.view.component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import susstore.susstore.controller.UpdateMemberController;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;

public class MemberCardComponent {
    private final BorderPane componentRootLayout;
    private final VBox idAndMembershipContainer;
    private final HBox statusAndActionsContainer;
    private final VBox nameAndPhoneContainer;
    private Member containMember;
    private UpdateMemberController updateMemberController;

    public MemberCardComponent(Member mm, UpdateMemberController updateMemberController) {
        this.componentRootLayout = new BorderPane();
        this.idAndMembershipContainer = new VBox();
        this.statusAndActionsContainer = new HBox();
        this.nameAndPhoneContainer = new VBox();
        this.containMember = mm;
        this.updateMemberController = updateMemberController;
        loadUI();
    }

    private void loadUI() {
        // left
        Label membershipLabel = new Label(containMember.getMembership().toString());
        membershipLabel.getStyleClass().add("membership-label");
        Label idLabel = new Label(containMember.getUserID() + "");
        idLabel.getStyleClass().add("id-label");

        this.idAndMembershipContainer.getChildren().addAll(membershipLabel, idLabel);

        // center
        Label nameLabel = new Label(containMember.getNama());
        Label phonNumberLabel = new Label(containMember.getNoTelp());
        nameLabel.getStyleClass().add(containMember.getNama());
        phonNumberLabel.getStyleClass().add(containMember.getNoTelp());

        this.nameAndPhoneContainer.getChildren().addAll(nameLabel, phonNumberLabel);

        // right
        Button editButton = new Button("\uf4ff;");
        editButton.getStyleClass().add("edit-action-button");
        editButton.setOnAction(
                e -> {
                    updateMemberController.setChoosenMember(containMember);
                }
        );

        Label statusLabel = new Label(containMember.getStatus() ? "ACTIVE" : "INACTIVE");
        statusLabel.getStyleClass().addAll("status-label", "status-label-active");

        VBox statusAndTransactionContainer = new VBox();
        statusAndTransactionContainer.getChildren().addAll(statusLabel);
        statusAndTransactionContainer.getStyleClass().add("status-transaction-container");

        this.statusAndActionsContainer.getChildren().addAll(statusAndTransactionContainer, editButton);


        // root layout
        this.componentRootLayout.setLeft(this.idAndMembershipContainer);
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
