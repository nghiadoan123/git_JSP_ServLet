package repository;

import bean.contract.Contract;
import bean.service.Service;

import java.util.List;
import java.util.Map;

public interface IContractRepository {

    List<Contract> findAll();
    Map<String,String> save(Contract contract);
    void remove(int id);
    Contract findById(int id);
    Map<String, String> update(Contract contract);
    List<Contract> findByName(String name);

}
