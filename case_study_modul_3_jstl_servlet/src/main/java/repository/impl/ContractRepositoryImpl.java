package repository.impl;

import bean.contract.AttachService;
import bean.contract.Contract;
import bean.contract.ContractDetail;
import bean.customer.Customer;
import bean.employee.Employee;
import bean.service.RentalType;
import bean.service.Service;
import bean.service.ServiceType;
import repository.IContractRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractRepositoryImpl implements IContractRepository {
    @Override
    public List<Contract> findAll() {
        List<Contract> contractList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("select * from contract");
            ResultSet resultSet = preparedStatement.executeQuery();
            Contract contract = null;
            Customer customer = null;
            Employee employee = null;
            Service service = null;
            while (resultSet.next()){
                contract = new Contract();
                customer = new Customer();
                employee = new Employee();
                service = new Service();
                contract.setId(Integer.parseInt(resultSet.getString("contract_id")));
                contract.setCheckIn(resultSet.getString("check_in"));
                contract.setCheckOut(resultSet.getString("check_out"));
                contract.setDeposit(Integer.parseInt(resultSet.getString("deposit")));
                contract.setTotalMoney(Integer.parseInt(resultSet.getString("total_money")));
                contract.setDeposit(Integer.parseInt(resultSet.getString("deposit")));

                customer.setId(Integer.parseInt(resultSet.getString("customer_id")));
                employee.setId(resultSet.getString("employee_id"));
                service.setId(resultSet.getString("service_id"));
                contract.setCustomer(customer);
                contract.setEmployee(employee);
                contract.setService(service);

                contractList.add(contract);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contractList;
    }

    @Override
    public Map<String, String> save(Contract contract) {
        Map<String,String> messageList = new HashMap<>();
        try {
            PreparedStatement preparedStatementCheck =
                    BaseRepository.connection.prepareStatement("select  * from contract where contract_id = ?");
            preparedStatementCheck.setString(1, String.valueOf(contract.getId()));
            ResultSet resultSet = preparedStatementCheck.executeQuery();
            if (resultSet.next()){
                messageList.put("sameId", "Input Same Id!");
                return messageList;
            }else {

                PreparedStatement preparedStatement =
                        BaseRepository.connection.prepareStatement("insert into contract(contract_id,employee_id,customer_id, service_id, check_in,check_out,deposit,total_money) " +
                                " values(?,?,?,?,?,?,?,?)");
                preparedStatement.setInt(1, contract.getId());
                preparedStatement.setString(2, contract.getEmployee().getId());
                preparedStatement.setInt(3, contract.getCustomer().getId());
                preparedStatement.setString(4, contract.getService().getId());
                preparedStatement.setString(5, contract.getCheckIn());
                preparedStatement.setString(6, contract.getCheckOut());
                preparedStatement.setInt(7, contract.getDeposit());
                preparedStatement.setInt(8, contract.getTotalMoney());

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
                    BaseRepository.connection.prepareStatement("delete from contract where contract_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Contract findById(int id) {
        Contract contract = null;
        Customer customer = null;
        Employee employee = null;
        Service service = null;

        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("select  * from contract where contract_id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                service = new Service();
                contract = new Contract();
                employee = new Employee();
                customer = new Customer();

                contract.setId(Integer.parseInt(resultSet.getString("contract_id")));
                contract.setCheckIn(resultSet.getString("check_in"));
                contract.setCheckOut(resultSet.getString("check_out"));
                contract.setDeposit(Integer.parseInt(resultSet.getString("deposit")));
                contract.setTotalMoney(Integer.parseInt(resultSet.getString("total_money")));
                contract.setDeposit(Integer.parseInt(resultSet.getString("deposit")));

                customer.setId(Integer.parseInt(resultSet.getString("customer_id")));
                employee.setId(resultSet.getString("employee_id"));
                service.setId(resultSet.getString("service_id"));
                contract.setCustomer(customer);
                contract.setEmployee(employee);
                contract.setService(service);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contract;
    }

    @Override
    public Map<String, String> update(Contract contract) {
        Map<String, String> messageList = new HashMap<>();
        try {

            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("update contract set employee_id=?," +
                            "customer_id=?,service_id=?,check_in=?,check_out=?,deposit=?,total_money=? where contract_id=?");



            preparedStatement.setString(1,contract.getEmployee().getId());
            preparedStatement.setInt(2,contract.getCustomer().getId());
            preparedStatement.setString(3, contract.getService().getId());
            preparedStatement.setString(4,contract.getCheckIn());
            preparedStatement.setString(5,contract.getCheckOut());
            preparedStatement.setInt(6,contract.getDeposit());
            preparedStatement.setInt(7,contract.getTotalMoney());
            preparedStatement.setInt(8,contract.getId());
            preparedStatement.executeLargeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            messageList.put("message", "invalid class id");
            return messageList;
        }
        return messageList;
    }

    @Override
    public List<Contract> findByName(String name) {
       return null;
    }
}
