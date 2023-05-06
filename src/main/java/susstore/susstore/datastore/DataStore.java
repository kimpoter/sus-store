package susstore.susstore.datastore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class DataStore<T> {
    public enum TYPE {
        JSON,
        OBJ,
        XML
    }
    private ObjectStore<T[]> store;
    private List<T> data;

    public DataStore(String targetFile, Class<T> objClass, TYPE type) throws Exception {
        switch (type) {
            case JSON:
                this.store = new JSONAdapter<T[]>(targetFile, objClass);
                break;
            case XML:
                this.store = new XMLAdapter<T[]>(targetFile, objClass);
                break;
            case OBJ:
                this.store = new OBJAdapter<T[]>(targetFile, objClass);
                break;
        }
        try {
            loadData();
        }
        catch(Exception e) {
            this.data = new ArrayList<T>();
            throw e;
        }
    }
    public void storeData() throws Exception {
        this.store.storeObject((T[]) this.data.toArray());
    }

    public void loadData() throws Exception {
        this.data = Arrays.asList(this.store.loadObject());
    }

    public List<T> getData() {
        return data;
    }
}
