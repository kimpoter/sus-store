package susstore.susstore.controller;

import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.Storable;
import susstore.susstore.models.TemporaryBill;

import java.util.ArrayList;
import java.util.List;

public class TemporaryBillController {
    private int dataStore;
    private SubscriberManager subscribers;

    // TEMPORARY LIST TO STORE
    private List<TemporaryBill> temporaryBills;

    public TemporaryBillController() {
        this.temporaryBills = new ArrayList<TemporaryBill>();
    }

    public void addTemporaryBill(TemporaryBill temporaryBill) {
        this.temporaryBills.add(temporaryBill);
    }

    public void removeTemporaryBill(TemporaryBill temporaryBill) {
        this.temporaryBills.remove(temporaryBill);
    }

    public List<TemporaryBill> getTemporaryBills() {
        return this.temporaryBills;
    }

    public int getTemporaryBillsLength() {
        return this.temporaryBills.size();
    }

    public boolean isTemporaryBillsEmpty() {
        return this.temporaryBills.isEmpty();
    }
}
