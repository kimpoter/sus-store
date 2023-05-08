package susstore.susstore.models.wrappers;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Barang;

import java.util.List;

public class BarangWrapper implements Storable {
    private List<Barang> barangList;

    private BarangWrapper() {}

    public BarangWrapper(List<Barang> barangList) {
        this.barangList = barangList;
    }

    public List<Barang> getBarangList() {
        return barangList;
    }

}
