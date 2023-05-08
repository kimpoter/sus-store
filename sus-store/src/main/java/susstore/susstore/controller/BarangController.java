package susstore.susstore.controller;

import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.DataStoreController;
import susstore.susstore.datastore.Storable;
import susstore.susstore.datastore.DataStoreController.TYPE;
import susstore.susstore.models.Barang;
import susstore.susstore.models.wrappers.BarangWrapper;
import susstore.susstore.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.File;

public class BarangController {
    private SubscriberManager subscribers;
    private DataStoreController<BarangWrapper> dataStoreController;

    private List<Barang> barangs;

    public BarangController(){
        this.barangs = new ArrayList<Barang>();
        this.subscribers = new SubscriberManager();
        this.dataStoreController =
                new DataStoreController<>(BarangWrapper.class,
                        "sus-store/Barang.json",
                        DataStoreController.TYPE.JSON);
        File f = new File("sus-store/Barang.json");
        if(f.exists() && !f.isDirectory()) { 
            try {
                this.barangs = this.dataStoreController.loadData().getBarangList();
            } catch (Exception e) {
                // TODO: handle exception
            }
            
} 
    }

    public BarangController(List<Barang> barang){
        this.barangs = barang;
        this.subscribers = new SubscriberManager();
    }

    public void setBarangs(List<Barang> barang){
        this.barangs = barang;
        this.subscribers.notify();
    }

    public void addBarang(Barang b){
        this.barangs.add(b);
        this.subscribers.notifysubs("add-barang");
        try {
            this.dataStoreController.storeData(new BarangWrapper(barangs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Barang> getBarangs(){
        return this.barangs;
    }

    public void loadData(String s, TYPE t){
        this.dataStoreController.changeTarget(s+"/Barang." + t.name().toLowerCase(), t);
        try {
            this.barangs = this.dataStoreController.loadData().getBarangList();
            this.subscribers.notifysubs("new-data");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
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
        try {
            this.dataStoreController.storeData(new BarangWrapper(barangs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBarang(UUID idbarang){
        int i=0;
        for(Barang b:barangs){
            if(idbarang==b.getID())barangs.remove(i);
            i++;
        }
        this.subscribers.notifysubs("delete-barang");
    }
    
    public Barang getBarangByID(UUID idbarang){
        for(Barang b: barangs){
            if(b.getID()==idbarang)return b;
        }
        return null;
    }
}
