package susstore.susstore.datastore;

public class DataStoreController<T extends Storable> {
    public enum TYPE {
        JSON,
        OBJ,
        XML
    }

    private final Class<T> objClass;
    private DataStore<T> dataStore;
    private T data;

    public DataStoreController(Class<T> objClass, String targetPath, TYPE type) {
        this.objClass = objClass;
        changeTarget(targetPath, type);
        this.data = null;
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

    public String getTargetPath() {
        return this.dataStore.getTargetPath();
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
