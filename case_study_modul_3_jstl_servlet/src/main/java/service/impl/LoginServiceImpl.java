package service.impl;

import bean.user.Login;
import repository.ILoginRepository;
import repository.impl.LoginRepositoryImpl;
import service.ILoginService;

import java.util.List;

public class LoginServiceImpl implements ILoginService {

    ILoginRepository iLoginRepository = new LoginRepositoryImpl();

    @Override
    public boolean login(Login login) {
        return iLoginRepository.login(login);
    }

    @Override
    public void remove(String userName) {
        iLoginRepository.remove(userName);
    }

    @Override
    public List<Login> findAll() {
        return iLoginRepository.findAll();
    }
}
