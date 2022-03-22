package controller;

import bean.employee.Division;
import bean.employee.EducationDegree;
import bean.employee.Employee;
import bean.employee.Position;
import jdk.nashorn.internal.ir.RuntimeNode;
import repository.impl.GetInformationSQL;
import service.IEmployeeService;
import service.ILoginService;
import service.impl.EmployServiceImpl;
import service.impl.LoginServiceImpl;
import util.Validate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {

    IEmployeeService iEmployeeService = new EmployServiceImpl();
    ILoginService iLoginService = new LoginServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAction = request.getParameter("userAction");
        if (userAction == null){
            userAction ="";
        }
        switch (userAction){
            case "create":
                createEmployee(request,response);
                break;
            case "edit":
                updateEmployee(request,response);
                break;
            case "search":
                searchNameEmployee(request,response);
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
                deleteEmployee(request,response);
                break;
            default:
                employeeList(request,response);
                break;

        }
    }


    public void employeeList(HttpServletRequest request, HttpServletResponse response){
        List<Employee> employeeList = this.iEmployeeService.findAll();
        request.setAttribute("employeeList",employeeList);
        List<Position> positionList = GetInformationSQL.positionList();
        request.setAttribute("positionList",positionList);
        List<EducationDegree> educationDegreeList = GetInformationSQL.educationDegreeList();
        request.setAttribute("educationDegreeList",educationDegreeList);
        List<Division> divisionList = GetInformationSQL.divisionList();
        request.setAttribute("divisionList",divisionList);


        // phần sesion để lấy thông tin lưu sẵn trong tên nhân viên bỏ vào các trang
        HttpSession session = request.getSession();
        String username =  session.getAttribute("usernameinfo").toString();
        String password =  session.getAttribute("passwordinfo").toString();


        try {
            request.getRequestDispatcher("pages/employee/employeeList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showCreateForm(HttpServletRequest request, HttpServletResponse response){

        List<Division> divisionList = GetInformationSQL.divisionList();
        List<EducationDegree> educationDegreeList = GetInformationSQL.educationDegreeList();
        List<Position> positionList = GetInformationSQL.positionList();

        request.setAttribute("divisionList",divisionList);
        request.setAttribute("educationDegreeList",educationDegreeList);
        request.setAttribute("positionList",positionList);
        try {
            request.getRequestDispatcher("/pages/employee/create.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createEmployee(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String idCard = request.getParameter("id_card");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        Double salary = Double.parseDouble(request.getParameter("salary"));

        int degreeID = Integer.parseInt(request.getParameter("degree"));
        int divisionID = Integer.parseInt(request.getParameter("division"));
        int positionID = Integer.parseInt(request.getParameter("position"));

        Employee employee = new Employee();
        EducationDegree educationDegree = new EducationDegree();
        Division division = new Division();
        Position position = new Position();
        employee.setId(id);
        employee.setName(name);
        employee.setBirthDay(birthday);
        employee.setIdCard(idCard);
        employee.setPhone(phone);
        employee.setAddress(address);
        employee.setSalary(salary);
        employee.setEmail(email);

        educationDegree.setId(degreeID);
        division.setId(divisionID);
        position.setId(positionID);

        employee.setEducationDegree(educationDegree);
        employee.setDivision(division);
        employee.setPosition(position);

        Map<String,String> messageList = iEmployeeService.save(employee);
        if (!messageList.isEmpty()){
            request.setAttribute("employeeId",messageList.get("sameId"));
            request.setAttribute("empty",messageList.get("empty"));
            request.setAttribute("personalIDMess", messageList.get("personalId"));
            request.setAttribute("phoneNumberMess", messageList.get("phoneNumber"));
            request.setAttribute("salaryMess", messageList.get("salary"));
            request.setAttribute("emailMess", messageList.get("email"));
            request.setAttribute("employee", employee);
            showCreateForm(request,response);

        } else {
            this.iEmployeeService.save(employee);

            try {
               response.sendRedirect("/employee");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        Employee employee = this.iEmployeeService.findById(id);

        List<Division> divisionList = GetInformationSQL.divisionList();
        List<EducationDegree> educationDegreeList = GetInformationSQL.educationDegreeList();
        List<Position> positionList = GetInformationSQL.positionList();

        request.setAttribute("employee",employee);
        request.setAttribute("divisionList",divisionList);
        request.setAttribute("educationDegreeList",educationDegreeList);
        request.setAttribute("positionList",positionList);

        try {
            request.getRequestDispatcher("/pages/employee/edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateEmployee(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String id_card = request.getParameter("id_card");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");


        int degreeID = Integer.parseInt(request.getParameter("degree"));
        int divisionID = Integer.parseInt(request.getParameter("division"));
        int positionID = Integer.parseInt(request.getParameter("position"));

        Employee employee = new Employee();
        EducationDegree educationDegree = new EducationDegree();
        Division division = new Division();
        Position position = new Position();

        educationDegree.setId(degreeID);
        division.setId(divisionID);
        position.setId(positionID);

        employee.setId(id);
        employee.setName(name);
        employee.setBirthDay(birthday);
        employee.setIdCard(id_card);
        employee.setSalary(salary);
        employee.setPhone(phone);
        employee.setAddress(address);
        employee.setEmail(email);
        employee.setEducationDegree(educationDegree);
        employee.setDivision(division);
        employee.setPosition(position);


        Map<String,String> messageList = iEmployeeService.update(employee);
        if (!messageList.isEmpty()) {
            request.setAttribute("personalIDMess", messageList.get("personalId"));
            request.setAttribute("phoneNumberMess", messageList.get("phoneNumber"));
            request.setAttribute("emailMess", messageList.get("email"));
            request.setAttribute("message", messageList.get("message"));
            request.setAttribute("salaryMess", messageList.get("salary"));
            this.showEditForm(request,response);
        } else {
            // cách 2 dùng redirect
            try {
                response.sendRedirect("/employee");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        request.setAttribute("employee",employee);
//        try {
//            request.getRequestDispatcher("pages/employee/edit.jsp").forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }


    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        this.iEmployeeService.remove(id);

        String userName = request.getParameter("username");
        this.iLoginService.remove(userName);
        try {
            response.sendRedirect("/employee");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchNameEmployee(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("searchName");
        List<Employee> employeeList = this.iEmployeeService.findByName(name);
        request.setAttribute("employeeList",employeeList);
        try {
            request.getRequestDispatcher("pages/employee/employeeList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
