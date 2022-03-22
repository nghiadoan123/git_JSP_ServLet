package service.impl;

import bean.contract.Contract;
import repository.IContractRepository;
import repository.impl.ContractRepositoryImpl;
import service.IContractService;
import util.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractServiceImpl implements IContractService {

    IContractRepository iContractRepository = new ContractRepositoryImpl();
    @Override
    public List<Contract> findAll() {
        return iContractRepository.findAll();
    }

    @Override
    public Map<String, String> save(Contract contract) {
        Map<String, String> messageList = new HashMap<>();
        if (contract.getCheckIn().equals("") || contract.getCheckOut().equals("")) {
            messageList.put("empty", "please input information");
        }
        if (contract.getTotalMoney() < 0) {
            messageList.put("totalMoney", "Invalid total money!");
        }
        if (contract.getDeposit() < 0) {
            messageList.put("deposit", "Invalid deposit!");
        }
        String checkIn = contract.getCheckIn();
        String checkOut = contract.getCheckOut();
        if (checkIn.compareTo(checkOut)>0){
            messageList.put("checkInOut", "Invalid check in and check out day");
        }

        Map<String, String> messageRepo = iContractRepository.save(contract);
        if (!messageRepo.isEmpty()) {
            messageList.put("sameId", messageRepo.get("sameId"));
        }
        return messageList;
    }

    @Override
    public void remove(int id) {
        iContractRepository.remove(id);
    }

    @Override
    public Contract findById(int id) {
        return iContractRepository.findById(id);
    }

    @Override
    public Map<String, String> update(Contract contract) {
        Map<String, String> messageList = new HashMap<>();
        Map<String,String> messageListRepo = iContractRepository.update(contract);

        if (!messageListRepo.isEmpty()){
            messageList.put("message",messageListRepo.get("message"));
        }
        if (contract.getCheckIn().equals("") || contract.getCheckOut().equals("")) {
            messageList.put("empty", "please input information");
        }
        if (contract.getTotalMoney() < 0) {
            messageList.put("totalMoney", "Invalid total money!");
        }
        if (contract.getDeposit() < 0) {
            messageList.put("deposit", "Invalid deposit!");
        }
        String checkIn = contract.getCheckIn();
        String checkOut = contract.getCheckOut();
        if (checkIn.compareTo(checkOut)>0){
            messageList.put("checkInOut", "Invalid check in and check out day");
        }
        return messageList;
    }

    @Override
    public List<Contract> findByName(String name) {
        return iContractRepository.findByName(name);
    }
}
