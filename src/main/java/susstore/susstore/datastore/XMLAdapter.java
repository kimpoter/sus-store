package susstore.susstore.datastore;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


class test {
    public String a;
    public test(String a) {
        this.a = a;
    }
}

public class XMLAdapter<T> extends FileAdapter<T>{

    public XMLAdapter(String targetFile, Class<?> objClass) {
        super(targetFile, objClass);
    }

    @Override
    public void storeObject(T obj) {

    }

    @Override
    public T loadObject() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(this.objClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T data = (T) unmarshaller.unmarshal(new File(this.targetFile));
        return data;
    }

    public static void main(String[] args) throws JAXBException {
        XMLAdapter<test> adapter = new XMLAdapter<>("test.xml", test.class);
        test x = adapter.loadObject();
        System.out.println(x.a);
    }
    
}
