package susstore.susstore.models;

import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.Product;
import susstore.susstore.models.api.UseCurrency;

import java.util.ArrayList;

public class FixedBill extends Bill implements UseCurrency {
    private static Integer fixedBillCount;
    private static Currency currency = CurrencyIDR.getInstance();
    private ArrayList<BarangSnapshot> daftarBarang;
    private Double totalHarga;

    public FixedBill(TemporaryBill bill) {
        super(fixedBillCount, bill.getUserID());
        fixedBillCount++;

        this.totalHarga = 0.0;
        this.daftarBarang = new ArrayList<BarangSnapshot>();

        ArrayList<TemporaryBillEntry> billEntries = bill.getDaftarEntry();
        for (TemporaryBillEntry belanjaan : billEntries) {
            addEntry(belanjaan);
        }
    }

    private void addEntry(TemporaryBillEntry entry) {
        Product product = entry.getProduct();
        int jumlah = entry.getJumlah();

        Double entryHarga = product.getHargaJual();
        BarangSnapshot newEntry = new BarangSnapshot(
                product.getNama(),
                entryHarga,
                jumlah
        );
        this.daftarBarang.add(newEntry);

        this.totalHarga += entryHarga * jumlah;
    }

    @Override
    public Double getBillTotal() {
        return currency.getValue(this.totalHarga);
    }

    @Override
    public void setCurrency(Currency c) {
        currency = c;
    }
}

class BarangSnapshot {
    private String namaBarang;

    private Currency currency = CurrencyIDR.getInstance();

    private Double hargaBarang;

    private Integer jumlahBarang;

    public BarangSnapshot(
            String namaBarang,
            Double hargaBarang,
            Integer jumlahBarang
    ) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBarang = jumlahBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public Double getHargaBarang() {
        return this.hargaBarang;
    }

    public Integer getJumlahBarang() {
        return this.jumlahBarang;
    }

    public void setCurrency(Currency c) {
        this.currency = c;
    }

}