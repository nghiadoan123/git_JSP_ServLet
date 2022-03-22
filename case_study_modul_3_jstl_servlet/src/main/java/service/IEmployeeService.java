package service;

import bean.employee.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {

    List<Employee> findAll();
    Map<String,String> save(Employee employee);
    void remove(int id);
    Employee findById(String id);
    Map<String, String> update(Employee employee);
    List<Employee> findByName(String name);

}
