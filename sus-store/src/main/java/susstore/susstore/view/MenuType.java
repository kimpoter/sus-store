package susstore.susstore.view;

public enum MenuType {
    Settings("Settings"),
    Customer("Customer"),
    Barang("Barang"),
    AboutUs("About Us"),
    Plugins("Plugins");

    private final String name;

    private MenuType(String name) {
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
