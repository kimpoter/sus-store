package susstore.susstore.controller;

import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Barang;
import susstore.susstore.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BarangController {
    private SubscriberManager subscribers;

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
        this.subscribers.notifysubs("add-barang");
    }

    public List<Barang> getBarangs(){
        return this.barangs;
    }

    public void addSubscriber(Subscriber s){
        this.subscribers.subscribe(s);
    }

    public void editBarang(UUID idbarang,String nama, Integer stok, String kategori, String imagepath, Double hargaBarang, Double hargabelibarang){
        Barang b = getBarangByID(idbarang);
        b.setNama(nama);
        b.setHargaBeli(hargabelibarang);
        b.setHargaJual(hargaBarang);
        b.setKategori(kategori);
        b.setPathGambar(imagepath);
        b.setStok(stok);
        this.subscribers.notifysubs("add-barang");
    }
    
    public Barang getBarangByID(UUID idbarang){
        for(Barang b: barangs){
            if(b.getID()==idbarang)return b;
        }
        return null;
    }
}
