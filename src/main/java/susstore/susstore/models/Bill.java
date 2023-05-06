package susstore.susstore.models;


import susstore.susstore.datastore.Storable;

public abstract class Bill implements Storable {

    protected int id;
    protected int idUser;

    public Bill(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    /* Getter & Setter */
    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public abstract Nominal getBillTotal();

    @Override
    public String getStorableId() {
        return Integer.toString(this.id);
    }
}
