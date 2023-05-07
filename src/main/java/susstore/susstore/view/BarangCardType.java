package susstore.susstore.view;

public enum BarangCardType {
    Kasir("Kasir"),
    Gudang("Gudang");

    private final String name;

    private BarangCardType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
