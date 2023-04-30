package susstore.susstore;

public abstract class Bill {
    protected int idUser;

    public Bill(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }
}
