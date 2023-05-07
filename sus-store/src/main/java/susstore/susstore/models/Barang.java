package susstore.susstore.models;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.api.*;

import java.util.UUID;

public class Barang implements Product, Storable {
    private static Currency currency = CurrencyIDR.getInstance();
    private final UUID id;
    private String namaBarang;
    private int stok;
    private String kategori;
    private String pathGambar;
    private double hargaBeli;

    private double hargaJual;

    public Barang(
            String namaBarang,
            int stok,
            String kategori,
            String pathGambar,
            double hargaBeli,
            double hargaJual
    ) {
        this.id = UUID.randomUUID();
        this.namaBarang = namaBarang;
        this.stok = stok;
        this.kategori = kategori;
        this.pathGambar = pathGambar;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    protected Barang() {
        this.id = UUID.randomUUID();
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

    public int getStok() {
        return this.stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHargaBeli() {
        return currency.getValue(this.hargaBeli);
    }

    public void setHargaBeli(double hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public double getHargaJual() {
        return currency.getValue(this.hargaJual);
    }

    public void setHargaJual(double hargaJual) {
        this.hargaJual = hargaJual;
    }

    public void ambilBarang(int jumlah) throws Exception {
        if (jumlah > this.stok)
            throw new Exception("Tidak cukup stok");

        this.stok -= jumlah;
    }
}
