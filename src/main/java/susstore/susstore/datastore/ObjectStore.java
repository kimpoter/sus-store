package susstore.susstore.datastore;

public interface ObjectStore<T> {
    public void storeObject(T obj);
    public T loadObject();
}
