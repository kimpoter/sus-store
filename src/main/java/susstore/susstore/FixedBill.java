package susstore.susstore;

import java.util.List;
import java.util.ArrayList;

public class FixedBill extends Bill {
    protected List<BarangSnapshot> daftar;
    protected Nominal total;

    public FixedBill(TemporaryBill bill) {
        super(bill.idUser);

        // initialize list
        this.daftar = new ArrayList<BarangSnapshot>();
        List<TemporaryBillEntry> billEntries = bill.getDaftar();
        for (TemporaryBillEntry belanjaan : billEntries
             ) {
            addEntry(belanjaan);
        }

        // initialize total
        this.total = new Nominal();

    }

    @Override
    public Nominal getBillTotal() {
        return new Nominal(this.total);
    }

    private void addEntry(TemporaryBillEntry entry) {
        // add entry to list
        Barang barang = entry.getBarang();
        int jumlah = entry.getJumlah();

        Nominal entryNominal = new Nominal(barang.getHargaBarang());
        BarangSnapshot newEntry = new BarangSnapshot(barang.getNamaBarang(), entryNominal, jumlah);
        this.daftar.add(newEntry);

        // add price to total
        this.total.addNominal(entryNominal, jumlah);
    }
}

class BarangSnapshot {
    private String namaBarang;
    private Nominal hargaBarang;
    private int jumlah;

    public BarangSnapshot(String namaBarang, Nominal hargaBarang, int jumlah) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlah = jumlah;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public Nominal getHargaBarang() {
        return hargaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }
}