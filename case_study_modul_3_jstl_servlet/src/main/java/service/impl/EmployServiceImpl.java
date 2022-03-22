package service.impl;

import bean.employee.Employee;
import repository.IEmployeeRepository;
import repository.impl.EmployeeRepositoryImpl;
import service.IEmployeeService;
import util.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployServiceImpl implements IEmployeeService {
    IEmployeeRepository iEmployeeRepository = new EmployeeRepositoryImpl();

    @Override
    public List<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Map<String, String> save(Employee employee) {
        Map<String, String> messageList = new HashMap<>();
        if (employee.getName().equals("") || employee.getAddress().equals("") || employee.getBirthDay().equals("")) {
            messageList.put("empty", "please input information");
        }
        if (!Validate.validatePersonalId(employee.getIdCard())) {
            messageList.put("personalId", "invalid personal id!");
        }
        if (!Validate.validatePhonenumber(employee.getPhone())) {
            messageList.put("phoneNumber", "invalid phone number!");
        }
        if (!Validate.validateEmail(employee.getEmail())) {
            messageList.put("email", "invalid email!");
        }
        if (employee.getSalary() < 0) {
            messageList.put("salary", "invalid salary!");
        }

        Map<String, String> messageRepo = iEmployeeRepository.save(employee);
        if (!messageRepo.isEmpty()) {
            messageList.put("sameId", messageRepo.get("sameId"));
        }

        return messageList;
    }

    @Override
    public void remove(int id) {
        iEmployeeRepository.remove(id);
    }

    @Override
    public Employee findById(String id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public  Map<String, String> update(Employee employee) {
        Map<String,String> messageList = new HashMap<>();
        Map<String,String> messageListRepo = iEmployeeRepository.update(employee);
        if (!messageListRepo.isEmpty()){
            messageList.put("message",messageListRepo.get("message"));
        }

        if (!Validate.validatePersonalId(employee.getIdCard())) {
            messageList.put("personalId", "invalid personal id!");
        }
        if (!Validate.validatePhonenumber(employee.getPhone())) {
            messageList.put("phoneNumber", "invalid phone number!");
        }
        if (!Validate.validateEmail(employee.getEmail())) {
            messageList.put("email", "invalid email!");
        }
        if (employee.getSalary() < 0) {
            messageList.put("salary", "invalid salary!");
        }

        return messageList;
    }

    @Override
    public List<Employee> findByName(String name) {
        return iEmployeeRepository.findByName(name);
    }

}
