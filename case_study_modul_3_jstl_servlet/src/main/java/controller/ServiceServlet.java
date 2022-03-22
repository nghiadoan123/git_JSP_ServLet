package controller;

import bean.customer.Customer;
import bean.customer.CustomerType;
import bean.employee.Division;
import bean.employee.EducationDegree;
import bean.employee.Employee;
import bean.employee.Position;
import bean.service.RentalType;
import bean.service.Service;
import bean.service.ServiceType;
import repository.impl.GetInformationSQL;
import repository.impl.ServiceRepositoryImpl;
import service.IServiceService;
import service.impl.ServiceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServiceServlet", urlPatterns = "/service")
public class ServiceServlet extends HttpServlet {
    IServiceService iServiceService = new ServiceServiceImpl();

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
            serviceList(request,response);
                break;

        }
    }

    public void serviceList(HttpServletRequest request, HttpServletResponse response){
        List<Service> serviceList = this.iServiceService.findAll();
        request.setAttribute("serviceList",serviceList);

        // phần sesion để lấy thông tin lưu sẵn trong tên nhân viên bỏ vào các trang
        HttpSession session = request.getSession();
        String username =  session.getAttribute("usernameinfo").toString();
        String password =  session.getAttribute("passwordinfo").toString();


        try {
            request.getRequestDispatcher("pages/service/serviceList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateForm(HttpServletRequest request, HttpServletResponse response){

        List<ServiceType> serviceTypeList = GetInformationSQL.serviceTypeList();
        List<RentalType> rentalTypeList = GetInformationSQL.rentalTypeList();

        request.setAttribute("serviceTypeList",serviceTypeList);
        request.setAttribute("rentalTypeList",rentalTypeList);

        try {
            request.getRequestDispatcher("/pages/service/create.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        int numberOfPerson = Integer.parseInt(request.getParameter("numberOfPerson"));
        String standardRoom = request.getParameter("standardRoom");
        String description = request.getParameter("description");
        int poolArea = Integer.parseInt(request.getParameter("poolArea"));
        int numberOfFloor = Integer.parseInt(request.getParameter("numberOfFloor"));


        int rentalTypeId = Integer.parseInt(request.getParameter("rentalType"));
        int serviceTypeId = Integer.parseInt(request.getParameter("serviceType"));


        Service service = new Service();
        RentalType rentalType = new RentalType();
        ServiceType serviceType = new ServiceType();

        service.setId(id);
        service.setName(name);
        service.setArea(area);
        service.setCost(cost);
        service.setNumberOfPerson(numberOfPerson);
        service.setStandardRoom(standardRoom);
        service.setDescription(description);
        service.setPoolArea(poolArea);
        service.setNumberOfFloor(numberOfFloor);

        rentalType.setId(rentalTypeId);
        serviceType.setId(serviceTypeId);

        service.setRentalType(rentalType);
        service.setServiceType(serviceType);


        Map<String, String> messageList = iServiceService.save(service);
        if (!messageList.isEmpty()) {
            request.setAttribute("sameId", messageList.get("sameId"));
            request.setAttribute("serviceId", messageList.get("serviceId"));
            request.setAttribute("empty", messageList.get("empty"));
            request.setAttribute("serviceId", messageList.get("personalId"));
            request.setAttribute("area", messageList.get("area"));
            request.setAttribute("cost", messageList.get("cost"));
            request.setAttribute("numberOfPerson", messageList.get("numberOfPerson"));
            request.setAttribute("poolArea", messageList.get("poolArea"));
            request.setAttribute("numberOfFloor", messageList.get("numberOfFloor"));
            showCreateForm(request, response);

        } else {
            iServiceService.save(service);

            try {
                response.sendRedirect("/service");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        iServiceService.remove(id);
        try {
            response.sendRedirect("/service");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("searchName");
        List<Service> serviceList = this.iServiceService.findByName(name);
        request.setAttribute("serviceList",serviceList);
        try {
            request.getRequestDispatcher("pages/service/serviceList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        Service service = this.iServiceService.findById(id);
        List<RentalType> rentalTypeList = GetInformationSQL.rentalTypeList();
        List<ServiceType> serviceTypeList = GetInformationSQL.serviceTypeList();


        request.setAttribute("edit",service);
        request.setAttribute("rentalTypeList",rentalTypeList);
        request.setAttribute("serviceTypeList",serviceTypeList);

        try {
            request.getRequestDispatcher("/pages/service/edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(HttpServletRequest request, HttpServletResponse response){

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int area = Integer.parseInt(request.getParameter("area"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        int numberOfPerson = Integer.parseInt(request.getParameter("numberOfPerson"));
        String standardRoom = request.getParameter("standardRoom");
        String description = request.getParameter("description");
        int poolArea = Integer.parseInt(request.getParameter("poolArea"));
        int numberOfFloor = Integer.parseInt(request.getParameter("numberOfFloor"));


        int rentalTypeId = Integer.parseInt(request.getParameter("rentalType"));
        int serviceTypeId = Integer.parseInt(request.getParameter("serviceType"));


        RentalType rentalType = new RentalType();
        rentalType.setId(rentalTypeId);
        ServiceType serviceType = new ServiceType();
        serviceType.setId(serviceTypeId);

        Service service = new Service(id,name,  area, cost, numberOfPerson, rentalType, serviceType, standardRoom, description,poolArea,numberOfFloor);

        Map<String,String> messageList = iServiceService.update(service);
        if (!messageList.isEmpty()) {

            request.setAttribute("empty", messageList.get("empty"));
            request.setAttribute("area", messageList.get("area"));
            request.setAttribute("cost", messageList.get("cost"));
            request.setAttribute("numberOfPerson", messageList.get("numberOfPerson"));
            request.setAttribute("poolArea", messageList.get("poolArea"));
            request.setAttribute("numberOfFloor", messageList.get("numberOfFloor"));
            this.showEditForm(request,response);
        }else {
            try {
                response.sendRedirect("/service");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
