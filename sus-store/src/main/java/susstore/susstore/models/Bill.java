package susstore.susstore.models;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.api.*;

import java.util.UUID;

public abstract class Bill implements Transaction, Storable
{
    protected UUID billID;

    protected UUID userID;

    public Bill(UUID userID)
    {
        this.billID = UUID.randomUUID();
        this.userID = userID;
    }

    protected Bill() {}

    @Override
    public UUID getID()
    {
        return this.billID;
    }

    public UUID getUserID()
    {
        return this.userID;
    }

    public abstract double getBillTotal();
}
