package susstore.susstore.models.wrappers;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerWrapper implements Storable {
    private List<Customer> customerList;
    private List<Member> memberList;
    private List<UUID> VIPList;

    public CustomerWrapper(List<Customer> customerList,
                           List<Member> memberList) {
        this.customerList = customerList;
        this.memberList = memberList;
        this.VIPList = new ArrayList<>();
        for (Member member: this.memberList
             ) {
            if(member.getMembership().equals(Member.MEMBERSHIP.VIP))
                this.VIPList.add(member.getUserID());
        }
    }

    private CustomerWrapper() {}

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
