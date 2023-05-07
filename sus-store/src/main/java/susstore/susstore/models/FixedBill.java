package susstore.susstore.models;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.Product;
import susstore.susstore.models.api.UseCurrency;

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

    @Override
    public double getBillTotal() {
        return currency.getValue(this.totalHarga);
    }

    @Override
    public void setCurrency(Currency c) {
        currency = c;
    }
}

class BarangSnapshot implements Storable {
    private String namaBarang;

    private Currency currency = CurrencyIDR.getInstance();

    private double hargaBarang;

    private int jumlahBarang;

    public BarangSnapshot(
            String namaBarang,
            Double hargaBarang,
            Integer jumlahBarang
    ) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBarang = jumlahBarang;
    }

    private BarangSnapshot() {}

    public String getNamaBarang() {
        return namaBarang;
    }

    public double getHargaBarang() {
        return this.hargaBarang;
    }

    public int getJumlahBarang() {
        return this.jumlahBarang;
    }

    public void setCurrency(Currency c) {
        this.currency = c;
    }

}