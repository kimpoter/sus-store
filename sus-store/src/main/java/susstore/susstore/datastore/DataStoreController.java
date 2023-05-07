package susstore.susstore.datastore;

public class DataStoreController<T extends Storable> {
    public enum TYPE {
        JSON,
        OBJ,
        XML
    }

    private String targetFile;
    private TYPE type;
    private Class<T> objClass;
    private DataStore<T> dataStore;
    private T data;

    public DataStoreController(Class<T> objClass, String targetFile, TYPE type) {
        this.targetFile = targetFile;
        this.objClass = objClass;
        this.targetFile = targetFile;
        changeTarget(targetFile, type);
        this.data = null;
    }

    public void changeTarget(String targetFile, TYPE type) {
        switch (type) {
            case JSON:
                this.dataStore = new JSONAdapter<>(targetFile, this.objClass);
                break;
            case XML:
                this.dataStore = new XMLAdapter<>(targetFile, this.objClass);
                break;
            case OBJ:
                this.dataStore = new OBJAdapter<>(targetFile, this.objClass);
                break;
        }
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void storeData() throws Exception {
        this.dataStore.storeObject(this.data);
    }

    public void loadData() throws Exception {
        this.data = this.dataStore.loadObject();
    }
}
