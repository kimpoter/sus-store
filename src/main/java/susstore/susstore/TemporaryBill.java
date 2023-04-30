package susstore.susstore;

import java.util.List;
import java.util.ArrayList;

public class TemporaryBill extends Bill {
    protected List<TemporaryBillEntry> daftar;

    public TemporaryBill(int idUser) {
        super(idUser);
        this.daftar = new ArrayList<TemporaryBillEntry>();
    }

    public List<TemporaryBillEntry> getDaftar() {
        return new ArrayList<TemporaryBillEntry>(daftar);
    }

    public void addBarang(Barang newBarang, int jumlah) {
        daftar.add(new TemporaryBillEntry(newBarang, jumlah));
    }

    public void removeBarang(int idx) {
        daftar.remove(idx);
    }

    public Nominal hitungTotalBill() {
        Nominal totalPrice = new Nominal();
        for (TemporaryBillEntry belanjaan : this.daftar
             ) {
            totalPrice.addNominal(belanjaan.getBarang().getHargaBarang(), belanjaan.getJumlah());
        }
        return totalPrice;
    }
}

class TemporaryBillEntry {
    private Barang barang;
    private int jumlah;

    public TemporaryBillEntry(Barang barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
