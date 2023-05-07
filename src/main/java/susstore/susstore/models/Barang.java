package susstore.susstore.models;

import susstore.susstore.datastore.Storable;

import java.awt.image.BufferedImage;
import java.util.UUID;

public class Barang implements Storable {
    protected UUID id;
    protected String namaBarang;
    protected int stok;
    protected String kategori;
    protected BufferedImage gambar;
    protected Nominal hargaBarang;
    protected Nominal hargaBeliBarang;

    public Barang(String namaBarang, int stok, String kategori, BufferedImage gambar, Nominal hargaBarang, Nominal hargaBeliBarang) {
        this.id                 = UUID.randomUUID();
        this.namaBarang         = namaBarang;
        this.stok               = stok;
        this.kategori           = kategori;
        this.gambar             = gambar;
        this.hargaBarang        = hargaBarang;
        this.hargaBeliBarang    = hargaBeliBarang;
    }

    /* Getter & Setter */
    public UUID getId() {
        return id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getStok() {
        return stok;
    }

    public String getKategori() {
        return kategori;
    }

    public BufferedImage getGambar() {
        return gambar;
    }

    public Nominal getHargaBarang() {
        return hargaBarang;
    }

    public Nominal getHargaBeliBarang() {
        return hargaBeliBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setGambar(BufferedImage gambar) {
        this.gambar = gambar;
    }

    public void setHargaBarang(Nominal hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public void setHargaBeliBarang(Nominal hargaBeliBarang) {
        this.hargaBeliBarang = hargaBeliBarang;
    }

    /* Modifier */
    public void ambilBarang(int jumlah) throws Exception {
        if(jumlah > this.stok) {
            throw new Exception("Tidak cukup stok");
        }
        this.stok -= jumlah;
    }
}
