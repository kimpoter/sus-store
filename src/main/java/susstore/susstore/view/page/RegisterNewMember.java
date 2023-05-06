package susstore.susstore.view.page;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import susstore.susstore.view.PageType;

public class RegisterNewMember extends Page {
    private final VBox pageRootLayout;
    private final HBox actionButtonsContainer;
    private final VBox formContainer;

    public RegisterNewMember() {
        super(PageType.RegisterNewMember);
        this.pageRootLayout = new VBox();
        this.actionButtonsContainer = new HBox();
        this.formContainer = new VBox();
        loadUI();
        setStylesheet();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        // action buttons
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        saveButton.getStyleClass().add("save-button");
        cancelButton.getStyleClass().add("cancel-button");

        this.actionButtonsContainer.getChildren().addAll(cancelButton, saveButton);

        // form
        Label nameLabel = new Label("Name:");
        TextField nameInput = new TextField();
        VBox nameInputContainer = new VBox();
        nameInput.setPromptText("Kurokawa Akane (required)");
        nameLabel.getStyleClass().add("input-label");
        nameInputContainer.getChildren().addAll(nameLabel, nameInput);

        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberInput = new TextField();
        VBox phoneNumberInputContainer = new VBox();
        phoneNumberInput.setPromptText("890839 (numbers only - required)");
        phoneNumberLabel.getStyleClass().add("input-label");
        phoneNumberInputContainer.getChildren().addAll(phoneNumberLabel, phoneNumberInput);

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

        HBox membershipAndStatusContainer = new HBox();
        membershipAndStatusContainer.getChildren().addAll(membershipInputContainer, statusInputContainer);
        membershipAndStatusContainer.getStyleClass().add("membership-status-container");

        this.formContainer.getChildren().addAll(nameInputContainer, phoneNumberInputContainer, membershipAndStatusContainer);

        // root layout
        this.pageRootLayout.getChildren().addAll(actionButtonsContainer, formContainer);
    }

    private void setStylesheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.actionButtonsContainer.getStyleClass().add("action-buttons-container");
        this.formContainer.getStyleClass().add("form-container");
        this.pageRootLayout.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/register-new-member.css");
    }
}
