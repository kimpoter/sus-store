package susstore.susstore.datastore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONAdapter<T extends Storable> extends FileAdapter<T> {

    public JSONAdapter(String targetFile, Class<T> objClass) {
        super(targetFile, objClass);
    }

    @Override
    public void storeObject(T obj) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonWriter writer = new JsonWriter(new FileWriter(this.targetPath));
        gson.toJson(obj, this.objClass, writer);
        writer.flush();
        writer.close();
    }

    @Override
    public T loadObject() throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader reader = new JsonReader(new FileReader(this.targetPath));
        return gson.fromJson(reader, this.objClass);
    }
}
