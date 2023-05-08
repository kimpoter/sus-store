package susstore.susstore.controller;

import susstore.susstore.Subscriber;
import susstore.susstore.SubscriberManager;
import susstore.susstore.models.TemporaryBill;
import susstore.susstore.datastore.DataStoreController;
import susstore.susstore.models.wrappers.TemporaryBillWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TemporaryBillController {
    private int dataStore;
    private SubscriberManager subscribers;
    private DataStoreController<TemporaryBillWrapper> dataStoreController;

    // TEMPORARY LIST TO STORE
    private List<TemporaryBill> temporaryBills;

    public TemporaryBillController() {
        this.temporaryBills = new ArrayList<TemporaryBill>();
        this.subscribers = new SubscriberManager();
        this.dataStoreController =
                new DataStoreController<>(TemporaryBillWrapper.class,
                        "TemporaryBill.json",
                        DataStoreController.TYPE.JSON);
        File f = new File("TemporaryBill.json");
        if(f.exists() && !f.isDirectory()) {
            try {
                this.temporaryBills = this.dataStoreController.loadData().getTemporaryBillList();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
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

    public void loadData(String s, DataStoreController.TYPE t){
        this.dataStoreController.changeTarget(s+"/Barang." + t.name().toLowerCase(), t);
        try {
            this.temporaryBills = this.dataStoreController.loadData().getTemporaryBillList();
            this.subscribers.notifysubs("new-data");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void addSubscriber(Subscriber s) {
        this.subscribers.subscribe(s);
    }
}
