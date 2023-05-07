package susstore.susstore.models.api;

import susstore.susstore.datastore.Storable;

public interface User extends Storable
{
    public double bayar(double nominal);
}
