package susstore.susstore.models;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.api.*;

import java.util.UUID;

public abstract class Bill implements Transaction
{
    protected Integer biilID;

    protected UUID userID;

    public Bill(Integer billID, UUID userID)
    {
        this.biilID = billID;
        this.userID = userID;
    }

    @Override
    public Integer getID()
    {
        return this.biilID;
    }

    public UUID getUserID()
    {
        return this.userID;
    }

    public abstract Double getBillTotal();

    public String getStorableId()
    {
        return this.biilID.toString();
    }
}
