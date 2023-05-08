package susstore.susstore.controller;

import susstore.susstore.SubscriberManager;
import susstore.susstore.datastore.Storable;
import susstore.susstore.models.Customer;
import susstore.susstore.models.Member;
import susstore.susstore.models.MemberVIP;
import susstore.susstore.models.Member.MEMBERSHIP;
import susstore.susstore.Subscriber;
import susstore.susstore.datastore.DataStoreController;
import susstore.susstore.models.wrappers.CustomerWrapper;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserController {
    private int dataStore;
    private SubscriberManager subscribers;
    private DataStoreController<CustomerWrapper> customerStore;

    // TEMPORARY LIST TO STORE
    private List<Customer> customers;
    private List<Member> members;
    private List<Integer> vips;

    public UserController() {
        this.customers = new ArrayList<Customer>();
        this.subscribers = new SubscriberManager();
        this.members = new ArrayList<Member>();
        this.vips = new ArrayList<Integer>();
        this.customerStore = 
        new DataStoreController<>(CustomerWrapper.class,
                "Customer.json",
                DataStoreController.TYPE.JSON);
    }

    public UserController(List<Customer> barang) {
        this.customers = barang;
        this.subscribers = new SubscriberManager();
    }

    public String addCustomer(Customer c) {
        this.customers.add(c);
        try {
            this.customerStore.storeData(new CustomerWrapper(customers, members));
        } catch (Exception e) {
        }
        this.subscribers.notifysubs("add-customer");
        return this.customers.get(this.customers.size() - 1).getUserID().toString();
        
    }
    public void addMemberFromC(Customer C,String a, String b){
        this.members.add(new Member(new Customer(),a,b));
        this.subscribers.notifysubs("add-member");
    }
    public void addMember(Member c) {
        this.members.add(c);
        this.subscribers.notifysubs("add-member");
        try {
            this.customerStore.storeData(new CustomerWrapper(customers, members));
        } catch (Exception e) {
        }
    }

    public void editMember(UUID id, String name, String phone, MEMBERSHIP type, boolean isActive) {
        Integer mid = getMemberIdxByID(id);
        Member m = members.get(mid);
        m.setNama(name);
        m.setNoTelp(phone);
        m.setStatus(isActive);
        if (m.getMembership() != type) {
            if (type == MEMBERSHIP.MEMBER) {
                deleteFromVIP(m, mid);
            } else {
                addToVIP(m, mid);
            }
        }
        this.subscribers.notifysubs("edit-member");
        try {
            this.customerStore.storeData(new CustomerWrapper(customers, members));
        } catch (Exception e) {
        }
    }

    public void addToVIP(Member m, Integer id) {
        this.members.set(id, new MemberVIP(m));
        this.subscribers.notifysubs("add-customer");
    }

    public void deleteFromVIP(Member m, Integer id) {
        this.members.set(id, new Member(m));
        this.subscribers.notifysubs("add-customer");
    }

    public Integer getMemberIdxByID(UUID id) {
        int counter = 0;
        for (Member m : members) {
            if (m.getUserID().equals(id)) return counter;
            counter++;
        }
        return null;
    }

    public void addVIP(Integer c) {
        this.vips.add(c);
        //this.subscribers.notifysubs();
    }

    public void setCustomers(List<Customer> customer) {
        this.customers = customer;
        //this.subscribers.notify();
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
        //this.subscribers.notify();
    }

    public Member getMemberbyUUID(UUID id){
        for(Member m: members){
            if (m.getUserID()==id)return m;
        }
        return null;
    }

    public List<Integer> getVIPs() {
        return this.vips;
    }

    public void setVIPs(List<Integer> vips) {
        this.vips = vips;
        //this.subscribers.notify();
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public void loadData(String s, DataStoreController.TYPE t){
        this.customerStore.changeTarget(s+"/Customer." + t.name().toLowerCase(), t);
        try {
            this.customers = this.customerStore.loadData().getCustomerList();
            this.subscribers.notifysubs("new-data");
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            this.members = this.customerStore.loadData().getMemberList();
            this.subscribers.notifysubs("new-data");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void addSubscriber(Subscriber s) {
        this.subscribers.subscribe(s);
    }
}
