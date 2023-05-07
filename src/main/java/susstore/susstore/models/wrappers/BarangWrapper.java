package susstore.susstore.models.wrappers;

import susstore.susstore.models.Barang;

import java.util.List;

public class BarangWrapper {
    private List<Barang> barangList;

    public BarangWrapper(List<Barang> barangList) {
        this.barangList = barangList;
    }

    public List<Barang> getBarangList() {
        return barangList;
    }

    public void setBarangList(List<Barang> barangList) {
        this.barangList = barangList;
    }
}
