package susstore.susstore.controller;

import susstore.susstore.Subscriber;
import susstore.susstore.SubscriberManager;
import susstore.susstore.models.Barang;

public class EditBarangController{
    private SubscriberManager subscriberManager;
    private Barang chosenBarang;
    public EditBarangController() {
        this.subscriberManager = new SubscriberManager();
    }

    public void update(String s) {

    }

    public void addSubscriber(Subscriber s){
        subscriberManager.subscribe(s);
    }

    public void notifysubs(String s){
        subscriberManager.notifysubs(s);
    }

    public void setBarang(Barang b){
        this.chosenBarang=b;
        subscriberManager.notifysubs("choose-new-barang");
    }
    public Barang getBarang(){
        return this.chosenBarang;
    }
}
