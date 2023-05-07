package susstore.susstore.models.api;

import susstore.susstore.datastore.Storable;

import java.util.UUID;

public interface Transaction extends Storable
{
    UUID getID();
}
