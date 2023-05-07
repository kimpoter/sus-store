package susstore.susstore.models.wrappers;

import susstore.susstore.models.TemporaryBill;

import java.util.List;

public class TemporaryBillWrapper {
    private List<TemporaryBill> temporaryBillList;

    public TemporaryBillWrapper(List<TemporaryBill> temporaryBillList) {
        this.temporaryBillList = temporaryBillList;
    }

    public List<TemporaryBill> getTemporaryBillList() {
        return temporaryBillList;
    }

    public void setTemporaryBillList(List<TemporaryBill> temporaryBillList) {
        this.temporaryBillList = temporaryBillList;
    }
}
