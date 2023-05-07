package susstore.susstore.models;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.api.*;

import java.util.UUID;

public class Barang implements Product {
    private static Currency currency = CurrencyIDR.getInstance();
    private final UUID id;
    private String namaBarang;
    private Integer stok;
    private String kategori;
    private String pathGambar;
    private Double hargaBeli;

    private Double hargaJual;

    public Barang(
            String namaBarang,
            Integer stok,
            String kategori,
            String pathGambar,
            Double hargaBeli,
            Double hargaJual
    ) {
        this.id = UUID.randomUUID();
        this.namaBarang = namaBarang;
        this.stok = stok;
        this.kategori = kategori;
        this.pathGambar = pathGambar;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    @Override
    public UUID getID() {
        return this.id;
    }

    @Override
    public String getNama() {
        return this.namaBarang;
    }

    @Override
    public void setNama(String nama) {
        this.namaBarang = nama;
    }

    @Override
    public String getKategori() {
        return this.kategori;
    }

    @Override
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    @Override
    public String getPathGambar() {
        return this.pathGambar;
    }

    @Override
    public void setPathGambar(String pathGambar) {
        this.pathGambar = pathGambar;
    }


    @Override
    public void setCurrency(Currency c) {
        currency = c;
    }

    public Integer getStok() {
        return this.stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Double getHargaBeli() {
        return currency.getValue(this.hargaBeli);
    }

    public void setHargaBeli(Double hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public Double getHargaJual() {
        return currency.getValue(this.hargaJual);
    }

    public void setHargaJual(Double hargaJual) {
        this.hargaJual = hargaJual;
    }

    public void ambilBarang(Integer jumlah) throws Exception {
        if (jumlah > this.stok)
            throw new Exception("Tidak cukup stok");

        this.stok -= jumlah;
    }
}
