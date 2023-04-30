package susstore.susstore;

import java.util.Map;
import java.util.HashMap;

public class Nominal {
    private String mataUang;
    private float nominal;
    private static Map<String, Float> kurs;
    private static Map<Integer, Float> konversiPoin;

    /* Constructors */
    public Nominal() {
        /* TO-DO: define default nominal type */
        this.mataUang = "";
        this.nominal = 0;
    }

    public Nominal(String mataUang, float nominal) {
        this.mataUang = mataUang;
        this.nominal = nominal;
    }

    public Nominal(Nominal other) {
        this.mataUang = other.mataUang;
        this.nominal = other.nominal;
    }

    public Nominal(String mataUang, int poin) {
        /* TO-DO: konversi poin ke nominal */
    }

    /* Getter & Setter */
    public String getMataUang() {
        return mataUang;
    }

    public float getNominal() {
        return nominal;
    }

    public void setNominal(float nominal) {
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

    public void addNominal(Nominal other, float multiple) {
        if(this.mataUang == other.mataUang) {
            this.nominal += other.nominal * multiple;
        }
        else {
            Nominal convertedNominal = new Nominal(other);
            convertedNominal.convertNominal(this.mataUang);
            this.nominal += convertedNominal.nominal * multiple;
        }
    }
}
