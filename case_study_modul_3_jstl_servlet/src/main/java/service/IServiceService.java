package service;

import bean.service.Service;

import java.util.List;
import java.util.Map;

public interface IServiceService {

    List<Service> findAll();
    Map<String,String> save(Service service);
    void remove(String id);
    Service findById(String id);
    Map<String, String> update(Service service);
    List<Service> findByName(String name);
}
