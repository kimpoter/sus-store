package susstore.susstore.models.wrappers;

import susstore.susstore.models.Customer;

import java.util.List;

public class CustomerWrapper {
    private List<Customer> customerList;

    public CustomerWrapper(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
