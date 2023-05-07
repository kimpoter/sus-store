package susstore.susstore.models;

import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.User;

import java.util.UUID;

public class Customer implements User
{
    protected static Currency currency = CurrencyIDR.getInstance();

    protected UUID userID;

    protected int jumlahTransaksi;

    public Customer()
    {
        this.userID = UUID.randomUUID();
        this.jumlahTransaksi = 0;
    }

    protected Customer(Customer other)
    {
        this.userID = other.userID;
        this.jumlahTransaksi = other.jumlahTransaksi;
    }

    public UUID getUserID()
    {
        return this.userID;
    }

    public int getJumlahTransaksi() {
        return this.jumlahTransaksi;
    }

    @Override
    public double bayar(double nominal) {
        this.jumlahTransaksi++;
        return currency.getValue(nominal);
    }
}
