package susstore.susstore.controller;

import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.Storable;

import java.util.ArrayList;
import java.util.List;

public class BarangController {
    private int dataStore;
    private SubscriberManager subscribers;

    // TEMPORARY LIST TO STORE
    private List<Storable> barangs;

    public BarangController(){
        this.barangs = new ArrayList<Storable>();
    }
}
