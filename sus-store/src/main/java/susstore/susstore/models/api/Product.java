package susstore.susstore.models.api;

import susstore.susstore.datastore.Storable;

public interface Product extends Gambar, ID, Kategori, Nama, Storable, UseCurrency
{
    int getStok();
    double getHargaBeli();

    double getHargaJual();

    void setStok(int stok);

    void setHargaBeli(double hargaBeli);

    void setHargaJual(double hargaJual);
}
