package susstore.susstore;

import java.lang.Math;

public class Member extends Customer implements PointHolder {
    private static final boolean ACTIVE = true;
    private static int INITIAL_POINT = 0;
    protected String nama;
    protected String noTelp;
    protected Boolean status;
    protected int point;

    public Member(Customer other, String nama, String noTelp) {
        super(other);
        this.nama = nama;
        this.noTelp = noTelp;
        this.status = ACTIVE;
        this.point = INITIAL_POINT;
    }

    public Member(Member other) {
        super(other);
        this.nama = other.nama;
        this.noTelp = other.noTelp;
        this.status = other.status;
        this.point = other.point;
    }

    public String getNama() {
        return nama;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Nominal bayar(Nominal harga) {
        Nominal hargaFinal = super.bayar(harga);

        // calculate point, and add it to member
        addPoint(nominalToPoint(hargaFinal));
        return hargaFinal;
    }

    public Nominal bayar(Nominal harga, int point) {
        Nominal convertedNominal = new Nominal(harga);
        convertedNominal.convertNominal(POINT_CURRENCY);
        Nominal pointDiscount = new Nominal(POINT_CURRENCY, point);

        if(pointDiscount.compareTo(harga) > 0) {
            this.point -= Math.ceil(convertedNominal.getNominal());
            convertedNominal.setNominal(0);
        }
        else {
            convertedNominal.subtractNominal(pointDiscount);
        }
        convertedNominal.convertNominal(harga.getMataUang());
        return bayar(convertedNominal);
    }

    @Override
    public int nominalToPoint(Nominal nominal) {
        Nominal convertedNominal = new Nominal(nominal);
        convertedNominal.convertNominal(POINT_CURRENCY);

        // calculate point, and add it to member
        int totalPoint = (int)(convertedNominal.getNominal() * POINT_DEFAULT_RATE);

        return totalPoint;
    }

    @Override
    public void addPoint(int point) {
        this.point += point;
    }

    @Override
    public void usePoint(int point) {
        if(point > this.point) throw new InsufficientPointException();
        this.point -= point;
    }

    @Override
    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public int getPoint() {
        return this.point;
    }
}
