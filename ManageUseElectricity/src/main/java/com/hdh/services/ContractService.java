package com.hdh.services;

import com.hdh.daos.ContractDao;
import com.hdh.models.Contract;

import java.util.List;

public class ContractService {
    private ContractDao contractDao = new ContractDao();

    public boolean addContract(Contract contract) {
        return contractDao.addContract(contract);
    }

    public List<Contract> getAllContract() {
        return contractDao.getAllContract();
    }

    public boolean deleteContract(Long id) {
        return contractDao.deleteContract(id);
    }

    public Contract findContractById(Long id) {
        return contractDao.findContractById(id);
    }

    public boolean updateContract(Contract contractUpdate) {
        return contractDao.updateContract(contractUpdate);
    }

    public Contract findContractByCustomer(Long id) {
        return contractDao.findContractByCustomer(id);
    }
}
