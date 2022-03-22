package repository;

import bean.customer.Customer;

import java.util.List;
import java.util.Map;

public interface ICustomerRepository {

    List<Customer> findAll();
    Map<String, String> save(Customer customer);
    void remove(int id);
    Customer findById(int id);
    Map<String, String> update(Customer employee);
    List<Customer> findByName(String name);
}
