package susstore.susstore;

import java.util.ArrayList;
import java.util.List;

public class FixedBillController {
    private int dataStore;
    private SubscriberManager subscribers;

    // TEMPORARY LIST TO STORE
    private List<Storable> fixedBills;

    public FixedBillController(){
        this.fixedBills = new ArrayList<Storable>();
    }
}
