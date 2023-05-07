package susstore.susstore.controller;

import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Barang;
import susstore.susstore.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class BarangController {
    private int dataStore;
    private SubscriberManager subscribers;

    // TEMPORARY LIST TO STORE
    private List<Barang> barangs;

    public BarangController(){
        this.barangs = new ArrayList<Barang>();
        this.subscribers = new SubscriberManager();
    }

    public BarangController(List<Barang> barang){
        this.barangs = barang;
        this.subscribers = new SubscriberManager();
    }

    public void setBarangs(List<Barang> barang){
        this.barangs = barang;
        //this.subscribers.notify();
    }

    public void addBarang(Barang b){
        this.barangs.add(b);
        this.subscribers.notifysubs();
    }

    public List<Barang> getBarangs(){
        return this.barangs;
    }

    public void addSubscriber(Subscriber s){
        this.subscribers.subscribe(s);
    }
}
