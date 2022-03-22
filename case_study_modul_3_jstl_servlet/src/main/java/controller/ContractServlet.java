package controller;

import bean.contract.AttachService;
import bean.contract.Contract;
import bean.contract.ContractDetail;
import bean.customer.Customer;
import bean.employee.Employee;
import bean.service.RentalType;
import bean.service.Service;
import bean.service.ServiceType;
import repository.impl.CustomerRepositoryImpl;
import repository.impl.EmployeeRepositoryImpl;
import repository.impl.GetInformationSQL;
import repository.impl.ServiceRepositoryImpl;
import service.IContractService;
import service.impl.ContractServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ContractServlet",urlPatterns = "/contract")
public class ContractServlet extends HttpServlet {
    IContractService iContractService = new ContractServiceImpl();
    CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
    EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl();
    ServiceRepositoryImpl serviceRepository = new ServiceRepositoryImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userAction = request.getParameter("userAction");
        if (userAction == null){
            userAction ="";
        }
        switch (userAction){
            case "create":
                createForm(request,response);
                break;
            case "edit":
                edit(request,response);
                break;
            case "search":
                search(request,response);
                break;
            default:

                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userAction = request.getParameter("userAction");
        if (userAction == null){
            userAction ="";
        }
        switch (userAction){
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            default:
                contractList(request,response);
                break;

        }
    }

    private void contractList(HttpServletRequest request, HttpServletResponse response) {
        List<Contract> contractList = this.iContractService.findAll();
        request.setAttribute("contractList",contractList);

        // phần sesion để lấy thông tin lưu sẵn trong tên nhân viên bỏ vào các trang
        HttpSession session = request.getSession();
        String username =  session.getAttribute("usernameinfo").toString();
        String password =  session.getAttribute("passwordinfo").toString();


        try {
            request.getRequestDispatcher("pages/contract/contractList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        iContractService.remove(id);
        try {
            response.sendRedirect("/contract");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("searchName"));
        Contract contract =  this.iContractService.findById(id);
        request.setAttribute("contract",contract);
        try {
            request.getRequestDispatcher("pages/contract/searchList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateForm(HttpServletRequest request, HttpServletResponse response){

        List<Customer> customerList = customerRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        List<Service> serviceList = serviceRepository.findAll();

        request.setAttribute("customerList",customerList);
        request.setAttribute("employeeList",employeeList);
        request.setAttribute("serviceList",serviceList);

        try {
            request.getRequestDispatcher("/pages/contract/create.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");
        int deposit = Integer.parseInt(request.getParameter("deposit"));
        int totalMoney = Integer.parseInt(request.getParameter("totalMoney"));




        int customerId = Integer.parseInt(request.getParameter("customer"));
        String employeeId = request.getParameter("employee");
        String serviceId = request.getParameter("service");


        Contract contract = new Contract();
        Customer customer = new Customer();
        Employee employee = new Employee();
        Service service = new Service();


        contract.setId(id);
        contract.setCheckIn(checkIn);
        contract.setCheckOut(checkOut);
        contract.setDeposit(deposit);
        contract.setTotalMoney(totalMoney);

        customer.setId(customerId);
        employee.setId(employeeId);
        service.setId(serviceId);

       contract.setCustomer(customer);
       contract.setEmployee(employee);
       contract.setService(service);


        Map<String, String> messageList = iContractService.save(contract);
        if (!messageList.isEmpty()) {
            request.setAttribute("sameId", messageList.get("sameId"));
            request.setAttribute("empty", messageList.get("empty"));
            request.setAttribute("totalMoney", messageList.get("totalMoney"));
            request.setAttribute("deposit", messageList.get("deposit"));
            request.setAttribute("checkInOut", messageList.get("checkInOut"));
            showCreateForm(request, response);

        } else {
            iContractService.save(contract);

            try {
                response.sendRedirect("/contract");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Contract contract = this.iContractService.findById(id);
        List<Customer> customerList = customerRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();
        List<Service> serviceList = serviceRepository.findAll();


        request.setAttribute("edit",contract);
        request.setAttribute("customerList",customerList);
        request.setAttribute("employeeList",employeeList);
        request.setAttribute("serviceList",serviceList);

        try {
            request.getRequestDispatcher("/pages/contract/edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(HttpServletRequest request, HttpServletResponse response){

        int id = Integer.parseInt(request.getParameter("id"));
        String checkIn = request.getParameter("checkIn");
        String checkOut = request.getParameter("checkOut");
        int deposit = Integer.parseInt(request.getParameter("deposit"));
        int totalMoney = Integer.parseInt(request.getParameter("totalMoney"));

        int customerId = Integer.parseInt(request.getParameter("customer"));
        String employeeId = request.getParameter("employee");
        String serviceId = request.getParameter("service");


        Customer customer = new Customer();
        customer.setId(customerId);
        Employee employee = new Employee();
        employee.setId(employeeId);
        Service service = new Service();
        service.setId(serviceId);

        Contract contract = new Contract(id,checkIn,  checkOut, deposit, totalMoney, customer, employee, service);

        Map<String,String> messageList = iContractService.update(contract);
        if (!messageList.isEmpty()) {

            request.setAttribute("sameId", messageList.get("sameId"));
            request.setAttribute("empty", messageList.get("empty"));
            request.setAttribute("totalMoney", messageList.get("totalMoney"));
            request.setAttribute("deposit", messageList.get("deposit"));
            request.setAttribute("checkInOut", messageList.get("checkInOut"));
            this.showEditForm(request,response);
        }else {
            try {
                response.sendRedirect("/contract");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
