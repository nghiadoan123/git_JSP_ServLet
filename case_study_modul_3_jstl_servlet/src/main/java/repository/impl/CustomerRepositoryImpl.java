package repository.impl;

import bean.customer.Customer;
import bean.customer.CustomerType;
import repository.ICustomerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryImpl implements ICustomerRepository {

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        try {
            Statement statement = BaseRepository.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");
            Customer customer = null;
            CustomerType customerType = null;
            while (resultSet.next()){


                customerType = new CustomerType();
                customerType.setId(Integer.parseInt(resultSet.getString("customer_type_id")));

                customer = new Customer();
                customer.setId(Integer.parseInt(resultSet.getString("customer_id")));
                customer.setName(resultSet.getString("customer_name"));
                customer.setBirthDay(resultSet.getString("birthday"));
                customer.setGender(resultSet.getString("gender"));
                customer.setIdCard(resultSet.getString("id_card"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setCustomerType(customerType);

                customerList.add(customer);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Map<String, String> save(Customer customer) {
        Map<String,String> messageList = new HashMap<>();
        try {
            PreparedStatement preparedStatementCheck =
                    BaseRepository.connection.prepareStatement("select  * from customer where customer_id = ?");
            preparedStatementCheck.setString(1, String.valueOf(customer.getId()));
            ResultSet resultSet = preparedStatementCheck.executeQuery();
            if (resultSet.next()) {
                messageList.put("sameId", "Input Same Id!");
                return messageList;
            } else {

                PreparedStatement preparedStatement =
                        BaseRepository.connection.prepareStatement("insert into customer(customer_id, customer_type_id, customer_name, birthday,gender,id_card,phone,email,address) " +
                                "values(?,?,?,?,?,?,?,?,?)");
                preparedStatement.setInt(1, customer.getId());
                preparedStatement.setInt(2, customer.getCustomerType().getId());
                preparedStatement.setString(3, customer.getName());
                preparedStatement.setString(4, customer.getBirthDay());
                preparedStatement.setString(5, customer.getGender());
                preparedStatement.setString(6, customer.getIdCard());
                preparedStatement.setString(7, customer.getPhone());
                preparedStatement.setString(8, customer.getEmail());
                preparedStatement.setString(9, customer.getAddress());
                preparedStatement.executeLargeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messageList;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("delete from customer where customer_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Customer findById(int id) {
//        List<Customer> customerList = findAll();
//        Customer customer = null;
//        for (Customer customer1: customerList) {
//            if (customer1.getId() == id){
//                customer = customer1;
//            }
//        }
        Customer customer = null;
        CustomerType customerType = null;
        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("select  * from customer where customer_id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customer = new Customer();
                customerType = new CustomerType();
                customer.setId(Integer.parseInt(resultSet.getString("customer_id")));
                customerType.setId(Integer.parseInt(resultSet.getString("customer_type_id")));
                customer.setName(resultSet.getString("customer_name"));
                customer.setBirthDay(resultSet.getString("birthday"));
                customer.setGender(resultSet.getString("gender"));
                customer.setIdCard(resultSet.getString("id_card"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setCustomerType(customerType);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    @Override
    public  Map<String, String> update(Customer customer) {
        Map<String, String> messageList = new HashMap<>();
        try {

            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("update customer set customer_type_id=?,customer_name=?," +
                            "birthday=?,gender=?,id_card=?,phone=?,email=?,address=? where customer_id=?");


            preparedStatement.setInt(1,customer.getCustomerType().getId());
            preparedStatement.setString(2,customer.getName());
            preparedStatement.setString(3,customer.getBirthDay());
            preparedStatement.setString(4,customer.getGender());
            preparedStatement.setString(5,customer.getIdCard());
            preparedStatement.setString(6,customer.getPhone());
            preparedStatement.setString(7,customer.getEmail());
            preparedStatement.setString(8,customer.getAddress());
            preparedStatement.setInt(9,customer.getId());
            preparedStatement.executeLargeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            messageList.put("message", "invalid class id");
            return messageList;
        }
        return messageList;
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customerList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("select * from customer "
                    + "where customer_name= ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();

            Customer customer = null;
            CustomerType customerType = null;
            while (resultSet.next()){
                customerType = new CustomerType();
                customer = new Customer();
                customer.setId(Integer.parseInt(resultSet.getString("customer_id")));
                customer.setName(resultSet.getString("customer_name"));
                customer.setBirthDay(resultSet.getString("birthday"));
                customer.setGender(resultSet.getString("gender"));
                customer.setIdCard(resultSet.getString("id_card"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));


                customerType.setId(Integer.parseInt(resultSet.getString("customer_type_id")));
                customer.setCustomerType(customerType);
                customerList.add(customer);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }
}
