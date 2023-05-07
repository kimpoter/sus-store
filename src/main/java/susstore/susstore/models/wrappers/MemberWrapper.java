package susstore.susstore.models.wrappers;

import susstore.susstore.models.Member;

import java.util.List;

public class MemberWrapper {
    private List<Member> memberList;

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
