package susstore.susstore.view.page;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.CustomerCardComponent;
import susstore.susstore.view.component.JoinDataTest;
import susstore.susstore.controller.UserController;
import susstore.susstore.controller.UpdateCustomerController;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;
import susstore.susstore.Subscriber;

public class AllCustomerPage extends Page implements Subscriber{
    private final SplitPane pageRootLayout;
    private UserController customerController;
    private ScrollPane customersContainerScroll;
    private UpdateCustomerController updateCustomerController;
    private TextField nameInput;
    private TextField phoneNumberInput;
    private Label nameLabel;

    public AllCustomerPage(UserController customerController) {
        super(PageType.AllCustomerPage);
        this.pageRootLayout = new SplitPane();
        this.customerController = customerController;
        this.updateCustomerController = new UpdateCustomerController();
        customersContainerScroll = new ScrollPane();
        loadUI();
        setStylesheet();
        this.tab.setContent(this.pageRootLayout);
        this.customerController.addSubscriber(this);   
        this.updateCustomerController.addSubscriber(this);
    }

    public void update(String s){
        if(s=="set-choosen-member"){
            if(this.updateCustomerController.getChoosenCustomer()!=null){
            this.nameInput.setText("");
            this.nameLabel.setText("ID: "+ this.updateCustomerController.getChoosenCustomer().getUserID() + "Name:");
            }
        }
        VBox customersContainer = new VBox();
        for(Customer c : customerController.getCustomers()){
            if(customerController.getMemberbyUUID(c.getUserID())==null){
                CustomerCardComponent card = new CustomerCardComponent(c,updateCustomerController);
                customersContainer.getChildren().add(card.getComponent());}
        }
        customersContainerScroll.setContent(customersContainer);
        customersContainer.getStyleClass().add("customers-container-all-customer");
    }

    private void loadUI() {

        VBox customersContainer = new VBox();
        for(Customer c : customerController.getCustomers()){
            if(customerController.getMemberbyUUID(c.getUserID())==null){
            CustomerCardComponent card = new CustomerCardComponent(c,updateCustomerController);
            customersContainer.getChildren().add(card.getComponent());}
        }
        
        customersContainer.getStyleClass().add("customers-container-all-customer");
        customersContainerScroll.setContent(customersContainer);
        customersContainerScroll.setFitToWidth(true);

        nameLabel = new Label("ID: "  + "Name:");
        nameInput = new TextField();
        VBox nameContainer = new VBox();
        nameLabel.getStyleClass().add("input-label-all-customer");
        nameInput.getStyleClass().add("input-all-customer");
        nameContainer.getChildren().addAll(nameLabel, nameInput);

        Label phonNumberLabel = new Label("Phone Number:");
        phoneNumberInput = new TextField();
        VBox phoneNumberContainer = new VBox();
        phonNumberLabel.getStyleClass().add("input-label-all-customer");
        phoneNumberInput.getStyleClass().add("input-all-customer");
        phoneNumberContainer.getChildren().addAll(phonNumberLabel, phoneNumberInput);

        VBox formContainer = new VBox();
        formContainer.getStyleClass().add("form-container-all-customer");
        formContainer.getChildren().addAll(nameContainer, phoneNumberContainer);

        Button cancelButton = new Button("Cancel");
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e->{
            customerController.addMember(new Member(updateCustomerController.getChoosenCustomer(),nameInput.getText(),phoneNumberInput.getText()));
        });
        HBox actionButtonsContainer = new HBox();
        cancelButton.getStyleClass().addAll("action-button-all-customer", "cancel-button-all-customer");
        saveButton.getStyleClass().addAll("action-button-all-customer", "save-button-all-customer");
        actionButtonsContainer.getStyleClass().add("action-buttons-container-all-customer");
        actionButtonsContainer.getChildren().addAll(cancelButton, saveButton);

        saveButton.setOnAction(event -> {
            this.customerController.addMemberFromC(this.updateCustomerController.getChoosenCustomer(),
            nameInput.getText(),phoneNumberInput.getText());
        });

        BorderPane formAndActionsContainer = new BorderPane();
        formAndActionsContainer.setCenter(formContainer);
        formAndActionsContainer.setBottom(actionButtonsContainer);
        formAndActionsContainer.getStyleClass().add("form-actions-container-all-customer");

        this.pageRootLayout.setDividerPositions(0.65, 0.35);
        this.pageRootLayout.getItems().addAll(customersContainerScroll, formAndActionsContainer);
    }


    private void setStylesheet() {
        this.pageRootLayout.getStyleClass().add("page-root-layout");
        this.pageRootLayout.getStylesheets().add("css/all-customer-page.css");
    }
}
