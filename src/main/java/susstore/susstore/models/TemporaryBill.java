package susstore.susstore.models;

import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.Product;
import susstore.susstore.models.api.UseCurrency;

import java.util.ArrayList;
import java.util.UUID;

public class TemporaryBill extends Bill implements UseCurrency {
    private static Integer temporaryBillCount = 0;

    private ArrayList<TemporaryBillEntry> daftarEntry;

    public TemporaryBill(UUID userID) {
        super(temporaryBillCount, userID);

        temporaryBillCount++;
        this.daftarEntry = new ArrayList<TemporaryBillEntry>();
    }

    public ArrayList<TemporaryBillEntry> getDaftarEntry() {
        return this.daftarEntry;
    }

    public void addProduct(Product newProduct, Integer jumlah) {
        daftarEntry.add(new TemporaryBillEntry(newProduct, jumlah));
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
    public Double getBillTotal() {
        Double totalPrice = 0.0;

        for (TemporaryBillEntry belanjaan : this.daftarEntry) {
            totalPrice += belanjaan.getProduct().getHargaJual() * belanjaan.getJumlah();
        }

        return totalPrice;
    }

    @Override
    public void setCurrency(Currency c) {

    }
}

class TemporaryBillEntry {
    private Product product;

    private Integer jumlah;

    public TemporaryBillEntry(Product product, Integer jumlah) {
        this.product = product;
        this.jumlah = jumlah;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getJumlah() {
        return this.jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
