package susstore.susstore.models.api;

import susstore.susstore.models.CurrencyIDR;

public interface Currency
{
    public double getValue(Double nominal);

    public int convertPoin(double harga);
}
