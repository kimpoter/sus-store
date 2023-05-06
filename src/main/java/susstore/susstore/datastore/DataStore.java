package susstore.susstore.datastore;

import java.util.List;

public class DataStore {
    private List<Storable> data;
    private Class type;

    public Storable getByID(String searchId) {
        for (Storable item: this.data
             ) {
            if(item.getStorableId() == searchId) {
                return item;
            }
        }
        return null;
    }

    public List<Storable> getData() {
        return data;
    }
}
