package repository.impl;

import bean.contract.AttachService;
import bean.contract.Contract;
import bean.contract.ContractDetail;
import bean.customer.CustomerType;
import bean.employee.Division;
import bean.employee.EducationDegree;
import bean.employee.Position;
import bean.service.RentalType;
import bean.service.ServiceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetInformationSQL {

    public static List<Division> divisionList() {
        List<Division> divisionList = new ArrayList<>();

        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from division");
            Division division = null;
            while (resultSet.next()){
                division = new Division();
                division.setId(Integer.parseInt(resultSet.getString("division_id")));
                division.setName(resultSet.getString("division_name"));
                divisionList.add(division);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisionList;
    }

    public static List<EducationDegree> educationDegreeList(){
        List<EducationDegree> educationDegreeList = new ArrayList<>();
        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from education_degree");
            EducationDegree educationDegree = null;
            while (resultSet.next()){
                educationDegree = new EducationDegree();
                educationDegree.setId(Integer.parseInt(resultSet.getString("degree_id")));
                educationDegree.setName(resultSet.getString("degree_name"));
                educationDegreeList.add(educationDegree);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return educationDegreeList;
    }

    public static List<Position> positionList(){
        List<Position> positionList = new ArrayList<>();
        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from position");
            Position position = null;
            while (resultSet.next()){
                position = new Position();
                position.setId(Integer.parseInt(resultSet.getString("position_id")));
                position.setName(resultSet.getString("position_name"));
                positionList.add(position);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return positionList;
    }

    public  static List<CustomerType> customerTypeList(){

        List<CustomerType> customerTypeList = new ArrayList<>();
        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from customer_type");
            CustomerType customerType = null;
            while (resultSet.next()){
                customerType = new CustomerType();
                customerType.setId(Integer.parseInt(resultSet.getString("customer_type_id")));
                customerType.setName(resultSet.getString("customer_type_name"));
                customerTypeList.add(customerType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerTypeList;
    }

    public  static List<RentalType> rentalTypeList(){

        List<RentalType> rentalTypeList = new ArrayList<>();
        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from rental_type");
            RentalType rentalType = null;
            while (resultSet.next()){
                rentalType = new RentalType();
                rentalType.setId(Integer.parseInt(resultSet.getString("rental_type_id")));
                rentalType.setName(resultSet.getString("rental_type_name"));
                rentalType.setCost(Double.parseDouble(resultSet.getString("price")));
                rentalTypeList.add(rentalType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rentalTypeList;
    }

    public  static List<ServiceType> serviceTypeList(){

        List<ServiceType> serviceTypeList = new ArrayList<>();
        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from service_type");
            ServiceType serviceType = null;
            while (resultSet.next()){
                serviceType = new ServiceType();
                serviceType.setId(Integer.parseInt(resultSet.getString("service_type_id")));
                serviceType.setName(resultSet.getString("service_type_name"));
                serviceTypeList.add(serviceType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return serviceTypeList;
    }

    public  static List<AttachService> attachServiceList(){

        List<AttachService> attachServiceList = new ArrayList<>();
        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from attach_service");
            AttachService attachService = null;
            while (resultSet.next()){
                attachService = new AttachService();
                attachService.setId(Integer.parseInt(resultSet.getString("a_id")));
                attachService.setName(resultSet.getString("a_name"));
                attachService.setCost(Double.valueOf(resultSet.getString("a_price")));
                attachService.setUnit(Integer.parseInt(resultSet.getString("unit")));
                attachService.setStatus(resultSet.getString("status"));
                attachServiceList.add(attachService);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return attachServiceList;
    }

    public  static List<ContractDetail> contractDetailList(){

        List<ContractDetail> contractDetailList = new ArrayList<>();
        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from detail_contract");
            ContractDetail contractDetail = null;
            Contract contract = null;
            AttachService attachService = null;
            while (resultSet.next()){
                contractDetail = new ContractDetail();
                contract = new Contract();
                attachService = new AttachService();
                contractDetail.setId(Integer.parseInt(resultSet.getString("detail_contract_id")));
                contractDetail.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
                contract.setId(Integer.parseInt(resultSet.getString("contract_id")));
                attachService.setId(Integer.parseInt(resultSet.getString("a_id")));
                contractDetail.setContractId(contract);
                contractDetail.setAttachServiceId(attachService);
                contractDetailList.add(contractDetail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contractDetailList;
    }

}
