package susstore.susstore.models;

import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.Product;
import susstore.susstore.models.api.UseCurrency;

import java.awt.*;
import java.util.ArrayList;

public class FixedBill extends Bill implements UseCurrency {
    private static Currency currency = CurrencyIDR.getInstance();
    private ArrayList<BarangSnapshot> daftarBarang;
    private double totalHarga;

    public FixedBill(TemporaryBill bill) {
        super(bill.getUserID());

        this.totalHarga = 0.0;
        this.daftarBarang = new ArrayList<>();

        ArrayList<TemporaryBillEntry> billEntries = bill.getDaftarEntry();
        for (TemporaryBillEntry belanjaan : billEntries) {
            addEntry(belanjaan);
        }
    }

    private FixedBill() {}

    private void addEntry(TemporaryBillEntry entry) {
        Product product = entry.getProduct();
        int jumlah = entry.getJumlah();

        double entryHarga = product.getHargaJual();
        BarangSnapshot newEntry = new BarangSnapshot(
                product.getNama(),
                entryHarga,
                jumlah
        );
        this.daftarBarang.add(newEntry);

        this.totalHarga += entryHarga * jumlah;
    }

    public ArrayList<BarangSnapshot> getDaftar() {
        return new ArrayList<>(this.daftarBarang);
    }

    @Override
    public double getBillTotal() {
        return currency.getValue(this.totalHarga);
    }

    @Override
    public void setCurrency(Currency c) {
        currency = c;
    }
}