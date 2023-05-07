package susstore.susstore.models;

import java.util.List;
import java.util.ArrayList;

public class TemporaryBill extends Bill {
    private static int temporaryBillCount = 0;
    protected List<TemporaryBillEntry> daftar;

    public TemporaryBill(int idUser) {
        super(temporaryBillCount, idUser);
        temporaryBillCount++;
        this.daftar = new ArrayList<TemporaryBillEntry>();
    }

    public List<TemporaryBillEntry> getDaftar() {
        return new ArrayList<TemporaryBillEntry>(daftar);
    }

    public int addBarang(Barang newBarang, int jumlah) {
        daftar.add(new TemporaryBillEntry(newBarang, jumlah));
        return daftar.size() - 1;
    }

    public void removeBarang(int idx) {
        daftar.remove(idx);
    }

    public boolean isBillValid() {
        boolean valid = true;
        for (TemporaryBillEntry belanjaan : this.daftar
        ) {
            valid &= belanjaan.getBarang().getStok() >= belanjaan.getJumlah();
        }
        return valid;
    }

    @Override
    public Nominal getBillTotal() {
        Nominal totalPrice = new Nominal();
        for (TemporaryBillEntry belanjaan : this.daftar
        ) {
            totalPrice.addNominal(belanjaan.getBarang().getHargaBarang(), belanjaan.getJumlah());
        }
        return totalPrice;
    }
}

