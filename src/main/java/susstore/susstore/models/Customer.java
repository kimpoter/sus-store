package susstore.susstore.models;

import susstore.susstore.datastore.Storable;

public class Customer implements Storable {
    private static int jumlahCustomer;
    protected int id;
    protected int jumlahTransaksi;

    public Customer() {
        this.id = jumlahCustomer;
        Customer.jumlahCustomer++;
        this.jumlahTransaksi = 1;
    }

    public Customer(Customer other) {
        this.id = other.id;
        this.jumlahTransaksi = other.jumlahTransaksi;
    }

    /* Getter & Setter */
    public int getId() {
        return id;
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public Nominal bayar(Nominal harga) {
        // do nothing (..yet, probably until user credit system is added)
        return new Nominal(harga);
    }

    @Override
    public String getStorableId() {
        return Integer.toString(this.id);
    }

}