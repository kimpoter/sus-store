package susstore.susstore.models;

public class MemberVIP extends Member {
    private static final double VIP_DISCOUNT = 0.10;
    public MemberVIP(Member other) {
        super(other);
        setMembership();
    }

    @Override
    protected void setMembership() {
        this.membership = MEMBERSHIP.VIP;
    }

    @Override
    public Nominal bayar(Nominal harga) {
        double newValue = harga.getNominal() * (1 - VIP_DISCOUNT);
        Nominal hargaVIP = new Nominal(harga.getMataUang(), newValue);
        return super.bayar(hargaVIP);
    }

    @Override
    public Nominal bayar(Nominal harga, int point) {
        double newValue = harga.getNominal() * (1 - VIP_DISCOUNT);
        Nominal hargaVIP = new Nominal(harga.getMataUang(), newValue);
        return super.bayar(hargaVIP, point);
    }
}
