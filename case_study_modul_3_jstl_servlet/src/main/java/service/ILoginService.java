package service;

import bean.user.Login;

import java.util.List;

public interface ILoginService {

    public  boolean login(Login login);
    void remove(String userName);
    List<Login> findAll();
}
