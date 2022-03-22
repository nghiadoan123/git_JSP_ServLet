package service.impl;

import bean.customer.Customer;
import repository.ICustomerRepository;
import repository.impl.CustomerRepositoryImpl;
import service.ICustomerService;
import util.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements ICustomerService {
    ICustomerRepository iCustomerRepository = new CustomerRepositoryImpl();
    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public Map<String, String> save(Customer customer) {
        Map<String, String> messageList = new HashMap<>();

        if (customer.getName().equals("") || customer.getAddress().equals("") || customer.getBirthDay().equals("")) {
            messageList.put("empty", "please input information");
        }
        if (!Validate.validatePersonalId(customer.getIdCard())) {
            messageList.put("personalId", "invalid personal id!");
        }
        if (!Validate.validatePhonenumber(customer.getPhone())) {
            messageList.put("phoneNumber", "invalid phone number!");
        }
        if (!Validate.validateEmail(customer.getEmail())) {
            messageList.put("email", "invalid email!");
        }


        Map<String, String> mesageRepo = iCustomerRepository.save(customer);
        if (!mesageRepo.isEmpty()) {
            messageList.put("sameId", mesageRepo.get("sameId"));
        }
        return messageList;
    }

    @Override
    public void remove(int id) {
        iCustomerRepository.remove(id);
    }

    @Override
    public Customer findById(int id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public Map<String, String> update(Customer customer) {
        Map<String,String> messageList = new HashMap<>();
        Map<String,String> messageListRepo = iCustomerRepository.update(customer);

        if (!messageListRepo.isEmpty()){
            messageList.put("message",messageListRepo.get("message"));
        }
        if (customer.getName().equals("") || customer.getAddress().equals("") || customer.getBirthDay().equals("")) {
            messageList.put("empty", "please input information");
        }
        if (!Validate.validatePersonalId(customer.getIdCard())) {
            messageList.put("personalId", "invalid personal id!");
        }
        if (!Validate.validatePhonenumber(customer.getPhone())) {
            messageList.put("phoneNumber", "invalid phone number!");
        }
        if (!Validate.validateEmail(customer.getEmail())) {
            messageList.put("email", "invalid email!");
        }

        return messageList;
    }

    @Override
    public List<Customer> findByName(String name) {
        return iCustomerRepository.findByName(name);
    }
}
