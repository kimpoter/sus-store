package susstore.susstore.models.wrappers;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;

import java.util.List;

public class CustomerWrapper implements Storable {
    private List<Customer> customerList;
    private List<Member> memberList;

    private CustomerWrapper() {}

    public CustomerWrapper(List<Customer> customerList,
                           List<Member> memberList) {
        this.customerList = customerList;
        this.memberList = memberList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }
}
