package susstore.susstore.models;

import susstore.susstore.models.api.*;

public class CurrencyIDR implements Currency, Poin
{
    private static CurrencyIDR IDR;

    private CurrencyIDR()
    {
        //IDR = new CurrencyIDR();
    }

    public static Currency getInstance()
    {
        if (IDR == null)
            IDR = new CurrencyIDR();

        return IDR;
    }

    public static Poin getPoinInstance()
    {
        if (IDR == null)
            IDR = new CurrencyIDR();

        return IDR;
    }

    public double getValue(Double nominal)
    {
        return nominal;
    }

    @Override
    public int convertPoin(double harga) {
        return (int)Math.ceil(harga / 1000.00);
    }

    public double convertCurrency(Integer poin)
    {
        return poin * 1000.00;
    }
}