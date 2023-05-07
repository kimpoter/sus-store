package susstore.susstore.controller;

import susstore.susstore.Subscriber;
import susstore.susstore.SubscriberManager;
import susstore.susstore.models.Member;

public class UpdateMemberController {
    private SubscriberManager subscribers;
    private Member choosenMember;
    public UpdateMemberController() {
        this.subscribers = new SubscriberManager();
        this.choosenMember = null;
    }

    public void setChoosenMember(Member m){
        choosenMember=m;
        this.subscribers.notifysubs("set-choosen-member");
    }

    public Member getChoosenMember(){
        return choosenMember;
    }

    public void addSubscriber(Subscriber s){
        this.subscribers.subscribe(s);
    }
}
