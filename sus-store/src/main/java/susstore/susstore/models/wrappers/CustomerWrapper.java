package susstore.susstore.models.wrappers;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;
import susstore.susstore.models.MemberVIP;

import java.util.ArrayList;
import java.util.List;

public class CustomerWrapper implements Storable {
    private List<Customer> customerList;
    private List<Member> memberList;
    private List<MemberVIP> VIPList;

    private CustomerWrapper() {}

    public CustomerWrapper(List<Customer> customerList,
                           List<Member> memberList) {
        this.customerList = customerList;
        this.memberList = memberList;
        this.VIPList = new ArrayList<>();
        for (Member member: memberList
             ) {
            if(member.getMembership().equals(Member.MEMBERSHIP.VIP)) {
                this.VIPList.add(new MemberVIP(member));
            }
        }
        memberList.removeIf(e -> (e.getMembership().equals(Member.MEMBERSHIP.VIP)));
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public List<Member> getMemberList() {
        this.memberList.addAll(this.VIPList);
        return this.memberList;
    }
}
