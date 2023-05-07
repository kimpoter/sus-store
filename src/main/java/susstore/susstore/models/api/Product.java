package susstore.susstore.models.api;

import susstore.susstore.datastore.Storable;

public interface Product extends Gambar, ID, Kategori, Nama, Storable, UseCurrency
{
    public Integer getStok();
    public Double getHargaBeli();

    public Double getHargaJual();

    public void setStok(Integer stok);

    public void setHargaBeli(Double hargaBeli);

    public void setHargaJual(Double hargaJual);
}
