package susstore.susstore.controller;

import susstore.susstore.Subscriber;
import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.DataStoreController;
import susstore.susstore.models.FixedBill;
import susstore.susstore.models.wrappers.FixedBillWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FixedBillController {
    private int dataStore;
    private SubscriberManager subscribers;
    private DataStoreController<FixedBillWrapper> dataStoreController;

    // TEMPORARY LIST TO STORE
    private List<FixedBill> fixedBills;

    public FixedBillController() {
        this.fixedBills = new ArrayList<FixedBill>();
        this.subscribers = new SubscriberManager();
        this.dataStoreController =
                new DataStoreController<>(FixedBillWrapper.class,
                        "FixedBill.json",
                        DataStoreController.TYPE.JSON);
        File f = new File("FixedBill.json");
        if(f.exists() && !f.isDirectory()) {
            try {
                this.fixedBills = this.dataStoreController.loadData().getFixedBillList();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public FixedBillController(List<FixedBill> fixedBills) {
        this.fixedBills = fixedBills;
        this.subscribers = new SubscriberManager();
    }

    public void addFixedBill(FixedBill fixedBill) {
        this.fixedBills.add(fixedBill);
        this.subscribers.notifysubs("");
    }

    public List<FixedBill> getFixedBills() {
        return this.fixedBills;
    }

    public void setFixedBills(List<FixedBill> fixedBills) {
        this.fixedBills = fixedBills;
    }

    public void addSubscriber(Subscriber s) {
        this.subscribers.subscribe(s);
    }
}
