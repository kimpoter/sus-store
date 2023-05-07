package susstore.susstore.controller;

import susstore.susstore.Subscriber;
import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.Storable;
import susstore.susstore.models.TemporaryBill;
import susstore.susstore.datastore.DataStoreController;
import susstore.susstore.models.wrappers.TemporaryBillWrapper;

import java.util.ArrayList;
import java.util.List;

public class TemporaryBillController {
    private int dataStore;
    private SubscriberManager subscribers;
    private DataStoreController<TemporaryBillWrapper> datastore;

    // TEMPORARY LIST TO STORE
    private List<TemporaryBill> temporaryBills;

    public TemporaryBillController() {
        this.temporaryBills = new ArrayList<TemporaryBill>();
        this.subscribers = new SubscriberManager();
    }

    public TemporaryBillController(List<TemporaryBill> temporaryBills) {
        this.temporaryBills = temporaryBills;
        this.subscribers = new SubscriberManager();
    }

    public void addTemporaryBill(TemporaryBill temporaryBill) {
        this.temporaryBills.add(temporaryBill);
        this.subscribers.notifysubs("");
    }

    public List<TemporaryBill> getTemporaryBills() {
        return this.temporaryBills;
    }

    public void setTemporaryBills(List<TemporaryBill> temporaryBills) {
        this.temporaryBills = temporaryBills;
    }

    public void addSubscriber(Subscriber s) {
        this.subscribers.subscribe(s);
    }
}
