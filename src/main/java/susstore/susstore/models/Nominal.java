package susstore.susstore.models;

import java.util.Map;

public class Nominal implements Comparable<Nominal> {
    private String mataUang;
    private double nominal;
    private static Map<String, Double> kurs;
    private static Map<Integer, Double> konversiPoin;

    /* Constructors */
    public Nominal() {
        /* TO-DO: define default nominal type */
        this.mataUang = "";
        this.nominal = 0;
    }

    public Nominal(String mataUang, double nominal) {
        this.mataUang = mataUang;
        this.nominal = nominal;
    }

    public Nominal(Nominal other) {
        this.mataUang = other.mataUang;
        this.nominal = other.nominal;
    }

    /* Getter & Setter */
    public String getMataUang() {
        return mataUang;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    /* Modifiers */
    public void convertNominal(String newMataUang) {
        /* TO-DO: konversi mata uang */
    }

    public void addNominal(Nominal other) {
        if(this.mataUang == other.mataUang) {
            this.nominal += other.nominal;
        }
        else {
            Nominal convertedNominal = new Nominal(other);
            convertedNominal.convertNominal(this.mataUang);
            this.nominal += convertedNominal.nominal;
        }
    }

    public void addNominal(Nominal other, double multiple) {
        if(this.mataUang == other.mataUang) {
            this.nominal += other.nominal * multiple;
        }
        else {
            Nominal convertedNominal = new Nominal(other);
            convertedNominal.convertNominal(this.mataUang);
            this.nominal += convertedNominal.nominal * multiple;
        }
    }

    public void subtractNominal(Nominal other) {
        if(this.mataUang == other.mataUang) {
            this.nominal -= other.nominal;
        }
        else {
            Nominal convertedNominal = new Nominal(other);
            convertedNominal.convertNominal(this.mataUang);
            this.nominal -= convertedNominal.nominal;
        }
    }

    public void subtractNominal(Nominal other, double multiple) {
        if(this.mataUang == other.mataUang) {
            this.nominal -= other.nominal * multiple;
        }
        else {
            Nominal convertedNominal = new Nominal(other);
            convertedNominal.convertNominal(this.mataUang);
            this.nominal -= convertedNominal.nominal * multiple;
        }
    }

    @Override
    public int compareTo(Nominal o) {
        Nominal convertedNominal = new Nominal(o);
        convertedNominal.convertNominal(this.mataUang);

        if(this.nominal < convertedNominal.nominal) return -1;
        if(this.nominal == convertedNominal.nominal) return 0;
        return 1;
    }
}
