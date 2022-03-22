package service.impl;

import bean.service.Service;
import repository.IServiceRepository;
import repository.impl.ServiceRepositoryImpl;
import service.IServiceService;
import util.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceServiceImpl implements IServiceService {
    IServiceRepository iServiceRepository = new ServiceRepositoryImpl();

    @Override
    public List<Service> findAll() {
        return iServiceRepository.findAll();
    }

    @Override
    public Map<String, String> save(Service service) {
        Map<String, String> messageList = new HashMap<>();
        if (service.getName().equals("") || service.getName().equals("")|| service.getDescription().equals("")) {
            messageList.put("empty", "please input information");
        }
        if (service.getArea() < 0) {
            messageList.put("area", "Invalid area!");
        }
        if (service.getCost() < 0) {
            messageList.put("cost", "Invalid cost!");
        }
        if (service.getNumberOfPerson() < 0) {
            messageList.put("numberOfPerson", "Invalid number of person!");
        }
        if (service.getNumberOfFloor() < 0) {
            messageList.put("numberOfFloor", "Invalid number of floor!");
        }
        if (service.getPoolArea() < 0) {
            messageList.put("poolArea", "Invalid pool area!");
        }
        if (!Validate.validateIdFacility(service.getId())){
            messageList.put("serviceId", "Invalid service id!");
        }
        Map<String, String> messageRepo = iServiceRepository.save(service);
        if (!messageRepo.isEmpty()) {
            messageList.put("sameId", messageRepo.get("sameId"));
        }

        return messageList;
    }

    @Override
    public void remove(String id) {
        this.iServiceRepository.remove(id);
    }

    @Override
    public Service findById(String id) {
        return this.iServiceRepository.findById(id);
    }

    @Override
    public Map<String, String> update(Service service) {
        Map<String,String> messageList = new HashMap<>();
        Map<String,String> messageListRepo = iServiceRepository.update(service);

        if (!messageListRepo.isEmpty()){
            messageList.put("message",messageListRepo.get("message"));
        }
        if (service.getName().equals("") || service.getDescription().equals("") || service.getStandardRoom().equals("")) {
            messageList.put("empty", "please input information");
        }
        if (service.getArea() < 0) {
            messageList.put("area", "Invalid area!");
        }
        if (service.getCost() < 0) {
            messageList.put("cost", "Invalid cost!");
        }
        if (service.getNumberOfPerson() < 0) {
            messageList.put("numberOfPerson", "Invalid number of person!");
        }
        if (service.getNumberOfFloor() < 0) {
            messageList.put("numberOfFloor", "Invalid number of floor!");
        }
        if (service.getPoolArea() < 0) {
            messageList.put("poolArea", "Invalid pool area!");
        }

        return messageList;
    }

    @Override
    public List<Service> findByName(String name) {
        return this.iServiceRepository.findByName(name);
    }
}
