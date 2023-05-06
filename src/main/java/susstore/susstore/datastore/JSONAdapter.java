package susstore.susstore.datastore;

public class JSONAdapter<T> extends FileAdapter<T> {

    public JSONAdapter(String targetFile, Class objClass) {
        super(targetFile, objClass);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void storeObject(T obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeObject'");
    }

    @Override
    public T loadObject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadObject'");
    }


}
