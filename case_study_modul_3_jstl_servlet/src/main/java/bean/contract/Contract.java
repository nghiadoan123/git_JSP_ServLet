package bean.contract;

import bean.customer.Customer;
import bean.employee.Employee;
import bean.service.Service;

public class Contract {
    private int id;
    private String checkIn;
    private String checkOut;
    private int deposit;
    private int totalMoney;
    private Customer customer;
    private Employee employee;
    private Service service;

    public Contract() {
    }

    public Contract(int id, String checkIn, String checkOut, int deposit, int totalMoney, Customer customer, Employee employee, Service service) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.deposit = deposit;
        this.totalMoney = totalMoney;
        this.customer = customer;
        this.employee = employee;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
