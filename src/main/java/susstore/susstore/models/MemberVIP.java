package susstore.susstore.models;

public class MemberVIP extends Member
{
    private static final double VIP_DISCOUNT = 0.1;

    public MemberVIP(Member other)
    {
        super(other);
        setMembership();
    }

    @Override
    protected void setMembership() {
        this.membership = MEMBERSHIP.VIP;
    }

    @Override
    public double bayar(double harga)
    {
        double newValue = currency.getValue(harga) * (1 - VIP_DISCOUNT);
        return super.bayar(newValue);
    }

    @Override
    public double bayar(double harga, int poin)
    {
        double newValue = currency.getValue(harga) * (1 - VIP_DISCOUNT);
        return super.bayar(newValue, poin);
    }
}
