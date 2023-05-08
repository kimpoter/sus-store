package susstore.susstore.models;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.User;

import java.util.UUID;

public class Customer implements User, Storable {
    protected static Currency currency = CurrencyIDR.getInstance();

    protected UUID userID;

    protected int jumlahTransaksi;

    public Customer() {
        this.userID = UUID.randomUUID();
        this.jumlahTransaksi = 0;
    }

    public Customer(UUID userID) {
        this.userID = userID;
        this.jumlahTransaksi = 0;
    }

    protected Customer(Customer other) {
        this.userID = other.userID;
        this.jumlahTransaksi = other.jumlahTransaksi;
    }

    public UUID getUserID() {
        return this.userID;
    }

    public int getJumlahTransaksi() {
        return this.jumlahTransaksi;
    }

    public double getFinalPrice(double nominal) {
        return currency.getValue(nominal);
    }

    @Override
    public double bayar(double nominal) {
        this.jumlahTransaksi++;
        return getFinalPrice(nominal);
    }
}
