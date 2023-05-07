package susstore.susstore.controller;

import susstore.susstore.Subscriber;
import susstore.susstore.SubscriberManager;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;

public class UpdateCustomerController {
    private SubscriberManager subscribers;
    private Customer choosenCustomer;
    public UpdateCustomerController() {
        this.subscribers = new SubscriberManager();
        this.choosenCustomer = null;
    }

    public void setChoosenCustomer(Customer m){
        choosenCustomer=m;
        this.subscribers.notifysubs("set-choosen-member");
    }

    public Customer getChoosenCustomer(){
        return choosenCustomer;
    }

    public void addSubscriber(Subscriber s){
        this.subscribers.subscribe(s);
    }
}
