package susstore.susstore.models;

import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.Product;
import susstore.susstore.models.api.UseCurrency;

import java.util.ArrayList;
import java.util.UUID;

public class TemporaryBill extends Bill implements UseCurrency {

    private ArrayList<TemporaryBillEntry> daftarEntry;

    public TemporaryBill(UUID userID) {
        super(userID);
        this.daftarEntry = new ArrayList<>();
    }

    protected TemporaryBill() {}

    public ArrayList<TemporaryBillEntry> getDaftarEntry() {
        return this.daftarEntry;
    }

    public int addProduct(Product newProduct, Integer jumlah) {
        daftarEntry.add(new TemporaryBillEntry(newProduct, jumlah));
        return daftarEntry.size() - 1;
    }

    public void removeBarang(int index) {
        daftarEntry.remove(index);
    }

    public boolean isBillValid() {
        boolean valid = true;

        for (TemporaryBillEntry belanjaan : this.daftarEntry) {
            valid &= belanjaan.getProduct().getStok() >= belanjaan.getJumlah();
        }

        return valid;
    }

    @Override
    public double getBillTotal() {
        double totalPrice = 0.0;

        for (TemporaryBillEntry belanjaan : this.daftarEntry) {
            totalPrice += belanjaan.getProduct().getHargaJual() * belanjaan.getJumlah();
        }

        return totalPrice;
    }

    @Override
    public void setCurrency(Currency c) {

    }
}

