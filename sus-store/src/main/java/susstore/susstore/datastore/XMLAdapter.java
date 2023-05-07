package susstore.susstore.datastore;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;
import susstore.susstore.models.wrappers.MemberWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Added dependency: (taruh di pom.xml)
//<dependency>
//<groupId>org.simpleframework</groupId>
//<artifactId>simple-xml</artifactId>
//<version>2.7.1</version>
//</dependency>

public class XMLAdapter<T extends Storable> extends FileAdapter<T>{

    public XMLAdapter(String targetFile, Class<T> objClass) {
        super(targetFile, objClass);
    }

    @Override
    public void storeObject(T obj) throws Exception  {
        File outputFile = new File(this.targetFile);

        Serializer serializer = new Persister();

        serializer.write(obj, outputFile);
    }

    @Override
    public T loadObject() throws Exception {
        File inputFile = new File(this.targetFile);

        Serializer serializer = new Persister();

        T data = (T) serializer.read(this.objClass, inputFile);

        return data;
    }
}
