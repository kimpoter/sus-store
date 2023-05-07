package susstore.susstore.models;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.api.*;

import java.util.UUID;

public abstract class Bill implements Transaction, Storable
{
    protected UUID biilID;

    protected UUID userID;

    public Bill(UUID userID)
    {
        this.biilID = UUID.randomUUID();
        this.userID = userID;
    }

    protected Bill() {}

    @Override
    public UUID getID()
    {
        return this.biilID;
    }

    public UUID getUserID()
    {
        return this.userID;
    }

    public abstract double getBillTotal();
}
