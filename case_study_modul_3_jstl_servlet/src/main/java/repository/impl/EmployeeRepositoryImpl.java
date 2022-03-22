package repository.impl;

import bean.employee.Division;
import bean.employee.EducationDegree;
import bean.employee.Employee;
import bean.employee.Position;
import repository.IEmployeeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryImpl implements IEmployeeRepository {
    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("select * from employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = null;
            Division division = null;
            EducationDegree educationDegree = null;
            Position position = null;
            while (resultSet.next()){
              employee = new Employee();
              division = new Division();
              educationDegree = new EducationDegree();
              position = new Position();
              employee.setId(resultSet.getString("employee_id"));
              employee.setName(resultSet.getString("employee_name"));
              employee.setBirthDay(resultSet.getString("birthday"));
              employee.setIdCard(resultSet.getString("id_card"));
              employee.setSalary(Double.parseDouble(resultSet.getString("salary")));
              employee.setPhone(resultSet.getString("phone"));
              employee.setEmail(resultSet.getString("email"));
              employee.setAddress(resultSet.getString("address"));

              division.setId(Integer.parseInt(resultSet.getString("division_id")));
              educationDegree.setId(Integer.parseInt(resultSet.getString("degree_id")));
              position.setId(Integer.parseInt(resultSet.getString("position_id")));

              employee.setPosition(position);
              employee.setEducationDegree(educationDegree);
              employee.setDivision(division);
              employeeList.add(employee);
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public Map<String, String> save(Employee employee) {
        Map<String,String> messageList = new HashMap<>();
        try {
            PreparedStatement preparedStatementCheck =
                    BaseRepository.connection.prepareStatement("select  * from employee where employee_id = ?");
            preparedStatementCheck.setString(1,employee.getId());
            ResultSet resultSet = preparedStatementCheck.executeQuery();
            if (resultSet.next()){
                messageList.put("sameId", "Input Same Id!");
                return messageList;
            }else {

                PreparedStatement preparedStatement =
                        BaseRepository.connection.prepareStatement("insert into employee(employee_id,employee_name,position_id, degree_id, division_id,birthday,id_card,salary," +
                                " phone,email,address) " +
                                " values(?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, employee.getId());
                preparedStatement.setString(2, employee.getName());
                preparedStatement.setInt(3, employee.getPosition().getId());
                preparedStatement.setInt(4, employee.getEducationDegree().getId());
                preparedStatement.setInt(5, employee.getDivision().getId());
                preparedStatement.setString(6, employee.getBirthDay());
                preparedStatement.setString(7, employee.getIdCard());
                preparedStatement.setDouble(8, employee.getSalary());
                preparedStatement.setString(9, employee.getPhone());
                preparedStatement.setString(10, employee.getEmail());
                preparedStatement.setString(11, employee.getAddress());

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
                    BaseRepository.connection.prepareStatement("delete from employee where employee_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Employee findById(String id) {
        Employee employee = null;
        Division division = null;
        EducationDegree educationDegree = null;
        Position position = null;
//        List< Employee > listEmployee = findAll();
//        Employee employee = null;
//        for (Employee list : listEmployee) {
//            if (list.getId().contains(id)) {
//                employee = list;
//            }
//        }
        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("select  * from employee where employee_id = ?");
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee = new Employee();
                division = new Division();
                educationDegree = new EducationDegree();
                position = new Position();


                employee.setId(resultSet.getString("employee_id"));
                employee.setName(resultSet.getString("employee_name"));
                employee.setBirthDay(resultSet.getString("birthday"));
                employee.setIdCard(resultSet.getString("id_card"));
                employee.setSalary(Double.parseDouble(resultSet.getString("salary")));
                employee.setPhone(resultSet.getString("phone"));
                employee.setEmail(resultSet.getString("email"));
                employee.setAddress(resultSet.getString("address"));


                division.setId(Integer.parseInt(resultSet.getString("division_id")));
                educationDegree.setId(Integer.parseInt(resultSet.getString("degree_id")));
                position.setId(Integer.parseInt(resultSet.getString("position_id")));


                employee.setPosition(position);
                employee.setEducationDegree(educationDegree);
                employee.setDivision(division);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    @Override
    public  Map<String, String> update(Employee employee) {
        Map<String, String> messageList = new HashMap<>();

        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("update employee set employee_name=?,position_id=?," +
                            "degree_id=?,division_id=?,birthday=?,id_card=?,salary=?,phone=?,email=?,address=? where employee_id=?");


            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getPosition().getId());
            preparedStatement.setInt(3, employee.getEducationDegree().getId());
            preparedStatement.setInt(4, employee.getDivision().getId());
            preparedStatement.setString(5, employee.getBirthDay());
            preparedStatement.setString(6, employee.getIdCard());
            preparedStatement.setDouble(7, employee.getSalary());
            preparedStatement.setString(8, employee.getPhone());
            preparedStatement.setString(9, employee.getEmail());
            preparedStatement.setString(10, employee.getAddress());
            preparedStatement.setString(11, employee.getId());
            preparedStatement.executeLargeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            messageList.put("message", "invalid class id");
            return messageList;
        }
        return messageList;
    }

    
    @Override
    public List<Employee> findByName(String name) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("select * from employee " +
                            "where employee_name=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = null;
            Position position = null;
            Division division = null;
            EducationDegree educationDegree = null;
            while (resultSet.next()){
                employee = new Employee();
                employee.setId(resultSet.getString("employee_id"));
                employee.setName(resultSet.getString("employee_name"));
                employee.setBirthDay(resultSet.getString("birthday"));
                employee.setIdCard(resultSet.getString("id_card"));
                employee.setSalary(Double.parseDouble(resultSet.getString("salary")));
                employee.setPhone(resultSet.getString("phone"));
                employee.setEmail(resultSet.getString("email"));
                employee.setAddress(resultSet.getString("address"));

                position = new Position();
                division = new Division();
                educationDegree = new EducationDegree();

                position.setId(Integer.parseInt(resultSet.getString("position_id")));
                division.setId(Integer.parseInt(resultSet.getString("division_id")));
                educationDegree.setId(Integer.parseInt(resultSet.getString("degree_id")));

                employee.setPosition(position);
                employee.setDivision(division);
                employee.setEducationDegree(educationDegree);

                employeeList.add(employee);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employeeList;
    }

}
