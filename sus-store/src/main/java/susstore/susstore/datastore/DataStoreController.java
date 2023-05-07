package susstore.susstore.datastore;

import susstore.susstore.SubscriberManager;
import susstore.susstore.Subscriber;

public class DataStoreController<T extends Storable> {
    public enum TYPE {
        JSON,
        OBJ,
        XML
    }

    private final Class<T> objClass;
    private DataStore<T> dataStore;
    private SubscriberManager subscriberManager;

    public DataStoreController(Class<T> objClass, String targetPath, TYPE type) {
        this.objClass = objClass;
        changeTarget(targetPath, type);
    }

    public void subscribe(Subscriber s){
        this.subscriberManager.subscribe(s);
    }

    public void changeTarget(String targetFile, TYPE type) {
        switch (type) {
            case JSON -> this.dataStore = new JSONAdapter<>(targetFile, this.objClass);
            case XML -> this.dataStore = new XMLAdapter<>(targetFile, this.objClass);
            case OBJ -> this.dataStore = new OBJAdapter<>(targetFile, this.objClass);
        }
    }

    public void setTargetPath(String targetPath) {
        this.dataStore.setTargetPath(targetPath);
    }

    public void storeData(T data) throws Exception {
        this.dataStore.storeObject(data);
    }

    public T loadData() throws Exception {
        return this.dataStore.loadObject();
    }
}
