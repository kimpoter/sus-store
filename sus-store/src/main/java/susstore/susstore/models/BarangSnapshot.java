package susstore.susstore.models;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.api.Currency;

class BarangSnapshot implements Storable {
    private String namaBarang;

    private Currency currency = CurrencyIDR.getInstance();

    private double hargaBarang;

    private int jumlahBarang;

    public BarangSnapshot(
            String namaBarang,
            double hargaBarang,
            int jumlahBarang
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