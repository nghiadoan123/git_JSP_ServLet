package service;

import bean.customer.Customer;

import java.util.List;
import java.util.Map;

public interface ICustomerService {

    List<Customer> findAll();
    Map<String, String> save(Customer customer);
    void remove(int id);
    Customer findById(int id);
    Map<String, String> update(Customer customer);
    List<Customer> findByName(String name);

}
