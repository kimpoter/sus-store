package susstore.susstore.datastore;

public abstract class FileAdapter<T extends Storable> implements DataStore<T> {
    protected String targetPath;
    protected Class<?> objClass;

    public FileAdapter(String targetPath, Class<T> objClass) {
        this.targetPath = targetPath;
        this.objClass = objClass;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
