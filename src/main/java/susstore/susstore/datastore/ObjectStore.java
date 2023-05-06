package susstore.susstore.datastore;

public interface ObjectStore<T> {
    void storeObject(T obj) throws Exception;
    T loadObject() throws Exception;
}
