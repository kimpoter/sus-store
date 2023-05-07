package susstore.susstore.controller;

import susstore.susstore.Subscriber;
import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.Storable;
import susstore.susstore.models.FixedBill;

import java.util.ArrayList;
import java.util.List;

public class FixedBillController {
    private int dataStore;
    private SubscriberManager subscribers;

    // TEMPORARY LIST TO STORE
    private List<FixedBill> fixedBills;

    public FixedBillController()
    {
        this.fixedBills = new ArrayList<FixedBill>();
        this.subscribers = new SubscriberManager();
    }

    public FixedBillController(List<FixedBill> fixedBills)
    {
        this.fixedBills = fixedBills;
        this.subscribers = new SubscriberManager();
    }

    public void setFixedBills(List<FixedBill> fixedBills)
    {
        this.fixedBills = fixedBills;
    }

    public void addFixedBill(FixedBill fixedBill)
    {
        this.fixedBills.add(fixedBill);
        this.subscribers.notifysubs();
    }

    public List<FixedBill> getFixedBills()
    {
        return this.fixedBills;
    }

    public void addSubscriber(Subscriber s)
    {
        this.subscribers.subscribe(s);
    }
}
