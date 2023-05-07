package susstore.susstore.models;

import susstore.susstore.models.api.Product;

public class TemporaryBillEntry {
    private Product product;

    private Integer jumlah;

    public TemporaryBillEntry(Product product, Integer jumlah) {
        this.product = product;
        this.jumlah = jumlah;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getJumlah() {
        return this.jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
