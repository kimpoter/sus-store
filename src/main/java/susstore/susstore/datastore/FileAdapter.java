package susstore.susstore.datastore;

public abstract class FileAdapter<T> implements ObjectStore<T> {
    protected String targetFile;
    protected Class<?> objClass;

    public FileAdapter(String targetFile, Class<?> objClass) {
        this.targetFile = targetFile;
        this.objClass = objClass;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }
}
