package susstore.susstore.datastore;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class OBJAdapter<T extends Storable> extends FileAdapter<T> {
    public OBJAdapter(String targetFile, Class<T> objClass) {
        super(targetFile, objClass);
    }

    @Override
    public void storeObject(T obj) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(this.targetPath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Override
    public T loadObject() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(this.targetPath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @SuppressWarnings("unchecked")
        T data = (T) objectInputStream.readObject();
        objectInputStream.close();
        return data;
    }
}
