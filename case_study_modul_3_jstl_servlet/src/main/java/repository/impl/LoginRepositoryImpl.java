package repository.impl;

import bean.user.Login;
import repository.ILoginRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRepositoryImpl implements ILoginRepository {
    @Override
    public boolean login(Login login) {
        boolean status = false;

        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("select * from login where username = ? and password = ?");

            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

    @Override
    public void remove(String userName) {
        try {
            PreparedStatement preparedStatement =
                    BaseRepository.connection.prepareStatement("delete from login\n" +
                            "where username = ?");
            preparedStatement.setString(1,userName);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Login> findAll() {
        List<Login> loginList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement("select * from login");
            ResultSet resultSet = preparedStatement.executeQuery();
            Login login = null;
            while (resultSet.next()){
                login = new Login();
                login.setUsername(resultSet.getString("username"));
                login.setPassword(resultSet.getString("password"));


                loginList.add(login);
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return loginList;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
