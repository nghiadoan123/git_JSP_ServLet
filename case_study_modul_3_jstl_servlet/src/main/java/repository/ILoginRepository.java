package repository;

import bean.user.Login;

import java.util.List;

public interface ILoginRepository {

    public  boolean login(Login login);
    void remove(String userName);
    List<Login> findAll();
}
