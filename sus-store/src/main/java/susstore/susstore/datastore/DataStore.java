package susstore.susstore.datastore;

public interface DataStore<T> {
    void storeObject(T obj) throws Exception;
    T loadObject() throws Exception;
}
