package susstore.susstore.models;

import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.User;

public class Customer implements User
{
    private static int jumlahCustomer = 0;

    protected Currency currency = CurrencyIDR.getInstance();

    protected int userID;

    protected int jumlahTransaksi;

    public Customer()
    {
        this.userID             = jumlahCustomer;
        Customer.jumlahCustomer++;
        this.jumlahTransaksi    = 1;
    }

    protected Customer(Customer other)
    {
        this.userID             = other.userID;
        this.jumlahTransaksi    = other.jumlahTransaksi;
    }

    public int getUserID()
    {
        return this.userID;
    }

    public int getJumlahTransaksi()
    {
        return this.jumlahTransaksi;
    }

    @Override
    public double bayar(double nominal) {
        return currency.getValue(nominal);
    }

    @Override
    public String getStorableId() {
        return Integer.toString(this.userID);
    }
}
