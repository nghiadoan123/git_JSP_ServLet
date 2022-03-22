package repository.impl;

import bean.employee.Division;
import bean.employee.EducationDegree;
import bean.employee.Employee;
import bean.employee.Position;
import bean.service.RentalType;
import bean.service.Service;
import bean.service.ServiceType;
import repository.IServiceRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceRepositoryImpl implements IServiceRepository {
    @Override
    public List<Service> findAll() {
        List<Service> serviceList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("select * from service");
            ResultSet resultSet = preparedStatement.executeQuery();
            Service service = null;
            RentalType rentalType = null;
            ServiceType serviceType = null;
            while (resultSet.next()){
                service = new Service();
                rentalType = new RentalType();
                serviceType = new ServiceType();
                service.setId(resultSet.getString("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setArea(Integer.parseInt(resultSet.getString("area")));
                service.setCost(Integer.parseInt(resultSet.getString("cost")));
                service.setNumberOfPerson(Integer.parseInt(resultSet.getString("number_of_person")));

                rentalType.setId(Integer.parseInt(resultSet.getString("rental_type_id")));
                serviceType.setId(Integer.parseInt(resultSet.getString("service_type_id")));

                service.setRentalType(rentalType);
                service.setServiceType(serviceType);
                service.setStandardRoom(resultSet.getString("standard_room"));
                service.setDescription(resultSet.getString("description_other_convenience"));
                service.setPoolArea(Integer.parseInt(resultSet.getString("area_pool")));
                service.setNumberOfFloor(Integer.parseInt(resultSet.getString("number_of_floors")));
                serviceList.add(service);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return serviceList;
    }

    @Override
    public Map<String, String> save(Service service) {
        Map<String,String> messageList = new HashMap<>();
        try {
            PreparedStatement preparedStatementCheck =
                    BaseRepository.connection.prepareStatement("select  * from service where service_id = ?");
            preparedStatementCheck.setString(1,service.getId());
            ResultSet resultSet = preparedStatementCheck.executeQuery();
            if (resultSet.next()){
                messageList.put("sameId", "Input Same Id!");
                return messageList;
            }else {

                PreparedStatement preparedStatement =
                        BaseRepository.connection.prepareStatement("insert into service(service_id,service_name,area, cost, number_of_person,rental_type_id,service_type_id,standard_room," +
                                " description_other_convenience,area_pool,number_of_floors) " +
                                " values(?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, service.getId());
                preparedStatement.setString(2, service.getName());
                preparedStatement.setInt(3, service.getArea());
                preparedStatement.setInt(4, service.getCost());
                preparedStatement.setInt(5, service.getNumberOfPerson());
                preparedStatement.setString(6, String.valueOf(service.getRentalType().getId()));
                preparedStatement.setString(7, String.valueOf(service.getServiceType().getId()));
                preparedStatement.setString(8, service.getStandardRoom());
                preparedStatement.setString(9, service.getDescription());
                preparedStatement.setString(10, String.valueOf(service.getPoolArea()));
                preparedStatement.setString(11, String.valueOf(service.getNumberOfFloor()));

                preparedStatement.executeLargeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messageList;
    }

    @Override
    public void remove(String id) {

        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("delete from service where service_id = ?");
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public Service findById(String id) {
//        List< Employee > listEmployee = findAll();
//        Employee employee = null;
//        for (Employee list : listEmployee) {
//            if (list.getId().contains(id)) {
//                employee = list;
//            }
//        }

        Service service = null;
        ServiceType serviceType = null;
        RentalType rentalType = null;

        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("select  * from service where service_id = ?");
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                service = new Service();
                serviceType = new ServiceType();
                rentalType = new RentalType();

                service.setId(resultSet.getString("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setArea(Integer.parseInt(resultSet.getString("area")));
                service.setCost(Integer.parseInt(resultSet.getString("cost")));
                service.setNumberOfPerson(Integer.parseInt(resultSet.getString("number_of_person")));

                rentalType.setId(Integer.parseInt(resultSet.getString("rental_type_id")));
                serviceType.setId(Integer.parseInt(resultSet.getString("service_type_id")));

                service.setRentalType(rentalType);
                service.setServiceType(serviceType);
                service.setStandardRoom(resultSet.getString("standard_room"));
                service.setDescription(resultSet.getString("description_other_convenience"));
                service.setPoolArea(Integer.parseInt(resultSet.getString("area_pool")));
                service.setNumberOfFloor(Integer.parseInt(resultSet.getString("number_of_floors")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return service;
    }

    @Override
    public Map<String, String> update(Service service) {
        Map<String, String> messageList = new HashMap<>();
        try {

            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("update service set service_name=?,area=?," +
                            "cost=?,number_of_person=?,rental_type_id=?,service_type_id=?,standard_room=?,description_other_convenience=?,area_pool=?,number_of_floors=? where service_id=?");


            preparedStatement.setString(1,service.getName());
            preparedStatement.setInt(2,service.getArea());
            preparedStatement.setInt(3,service.getCost());
            preparedStatement.setInt(4,service.getNumberOfPerson());
            preparedStatement.setInt(5,service.getRentalType().getId());
            preparedStatement.setInt(6,service.getServiceType().getId());
            preparedStatement.setString(7,service.getStandardRoom());
            preparedStatement.setString(8,service.getDescription());
            preparedStatement.setInt(9,service.getPoolArea());
            preparedStatement.setInt(10,service.getNumberOfFloor());
            preparedStatement.setString(11,service.getId());
            preparedStatement.executeLargeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            messageList.put("message", "invalid class id");
            return messageList;
        }
        return messageList;
    }

    @Override
    public List<Service> findByName(String name) {
        List<Service> serviceList = new ArrayList<>();
        Service service = null;
        ServiceType serviceType = null;
        RentalType rentalType = null;

        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("select  * from service where service_name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                service = new Service();
                rentalType = new RentalType();
                serviceType = new ServiceType();

                service.setId(resultSet.getString("service_id"));
                service.setName(resultSet.getString("service_name"));
                service.setArea(Integer.parseInt(resultSet.getString("area")));
                service.setCost(Integer.parseInt(resultSet.getString("cost")));
                service.setNumberOfPerson(Integer.parseInt(resultSet.getString("number_of_person")));

                rentalType.setId(Integer.parseInt(resultSet.getString("rental_type_id")));
                serviceType.setId(Integer.parseInt(resultSet.getString("service_type_id")));

                service.setRentalType(rentalType);
                service.setServiceType(serviceType);
                service.setStandardRoom(resultSet.getString("standard_room"));
                service.setDescription(resultSet.getString("description_other_convenience"));
                service.setPoolArea(Integer.parseInt(resultSet.getString("area_pool")));
                service.setNumberOfFloor(Integer.parseInt(resultSet.getString("number_of_floors")));

                serviceList.add(service);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return serviceList;
    }
}
