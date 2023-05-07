package susstore.susstore.models.wrappers;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.FixedBill;

import java.util.List;

public class FixedBillWrapper implements Storable {
    private List<FixedBill> fixedBillList;

    private FixedBillWrapper() {}

    public FixedBillWrapper(List<FixedBill> fixedBillList) {
        this.fixedBillList = fixedBillList;
    }

    public List<FixedBill> getFixedBillList() {
        return fixedBillList;
    }
}
