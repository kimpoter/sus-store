package susstore.susstore.models.wrappers;

import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Member;

import java.util.List;

public class MemberWrapper implements Storable {
    private List<Member> memberList;

    private MemberWrapper() {

    }

    public MemberWrapper(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }
}
