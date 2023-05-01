package susstore.susstore;

public abstract class Bill implements Storable {
    protected int idUser;

    public Bill(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public abstract Nominal getBillTotal();
}
