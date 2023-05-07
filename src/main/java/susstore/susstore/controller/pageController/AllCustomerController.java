package susstore.susstore.controller.pageController;

import javafx.scene.control.Label;
import susstore.susstore.Subscriber;

public class AllCustomerController implements Subscriber {
    private Label membershipLabel;
    private Label idLabel;
    private Label nameLabel;
    private Label phonNumberLabel;
    private Label statusLabel;
    private Label transactionLabel;

    public AllCustomerController(Label membershipLabel, Label idLabel, Label nameLabel, Label phonNumberLabel, Label statusLabel, Label transactionLabel) {
        this.membershipLabel = membershipLabel;
        this.idLabel = idLabel;
        this.nameLabel = nameLabel;
        this.phonNumberLabel = phonNumberLabel;
        this.statusLabel = statusLabel;
        this.transactionLabel = transactionLabel;
    }

    public Label getMembershipLabel() {
        return membershipLabel;
    }

    public void setMembershipLabel(Label membershipLabel) {
        this.membershipLabel = membershipLabel;
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Label idLabel) {
        this.idLabel = idLabel;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Label getPhonNumberLabel() {
        return phonNumberLabel;
    }

    public void setPhonNumberLabel(Label phonNumberLabel) {
        this.phonNumberLabel = phonNumberLabel;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(Label statusLabel) {
        this.statusLabel = statusLabel;
    }

    public Label getTransactionLabel() {
        return transactionLabel;
    }

    public void setTransactionLabel(Label transactionLabel) {
        this.transactionLabel = transactionLabel;
    }

    @Override
    public void update() {

    }
}
