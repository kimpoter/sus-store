package susstore.susstore.datastore;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class OBJAdapter<T> extends FileAdapter<T> {
    public OBJAdapter(String targetFile, Class<?> objClass) {
        super(targetFile, objClass);
    }

    @Override
    public void storeObject(T obj) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(this.targetFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Override
    public T loadObject() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(this.targetFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T data = (T) objectInputStream.readObject();
        objectInputStream.close();
        return data;
    }
}
