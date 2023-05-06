package susstore.susstore;

import java.util.ArrayList;
import java.util.List;

import susstore.susstore.models.Storable;

public class UserController {
    private int dataStore;
    private SubscriberManager subscribers;

    // TEMPORARY LIST TO STORE
    private List<Storable> users;

    public UserController(){
        this.users = new ArrayList<Storable>();
    }
}
