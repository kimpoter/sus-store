package susstore.susstore.models;

import susstore.susstore.models.api.Currency;
import susstore.susstore.models.api.Poin;
import susstore.susstore.models.api.UseCurrency;
import susstore.susstore.models.api.UsePoin;

public class Member extends Customer implements UseCurrency, UsePoin
{
    private static final boolean ACTIVE = true;

    private static final double pointConversionRate = 0.01;

    protected  String nama;

    protected String noTelp;

    protected boolean status;

    protected Poin poin = CurrencyIDR.getPoinInstance();

    protected int userPoin;

    public Member(
            Customer other,
            String nama,
            String noTelp
    )
    {
        super(other);
        this.nama   = nama;
        this.noTelp = noTelp;
        this.status = ACTIVE;
        this.userPoin   = 0;
    }

    public Member(Member other)
    {
        super(other);
        this.nama   = other.nama;
        this.noTelp = other.noTelp;
        this.status = other.status;
        this.userPoin   = other.userPoin;
    }

    public String getNama()
    {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public double bayar(double harga)
    {
        double hargaTotal = super.bayar(harga);

        addPoin((int)(hargaTotal * pointConversionRate));

        return hargaTotal;
    }

    public double bayar(double harga, int poinBayar)
    {
        double poinToCurrency = poin.convertCurrency(poinBayar);

        double convertedHarga = currency.getValue(harga);

        if (poinToCurrency > convertedHarga)
        {
            this.userPoin -= currency.convertPoin(convertedHarga);
            convertedHarga = 0;
        }
        else
        {
            convertedHarga -= poinToCurrency;
        }

        return bayar(convertedHarga);
    }

    @Override
    public void setCurrency(Currency c)
    {
        currency = c;
    }


    @Override
    public void addPoin(int poin) {
        this.userPoin += poin;
    }

    @Override
    public void usePoin(int poin) {
        this.userPoin -= poin;
    }

    @Override
    public void setPoin(int poin) {
        this.userPoin = poin;
    }

    @Override
    public int getPoin() {
        return this.userPoin;
    }
}
