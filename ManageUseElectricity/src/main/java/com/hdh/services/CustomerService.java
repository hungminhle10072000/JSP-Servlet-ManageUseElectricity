package com.hdh.services;

import com.hdh.daos.CustomerDao;
import com.hdh.models.Business;
import com.hdh.models.Customer;
import com.hdh.models.HouseHold;

import java.util.List;

public class CustomerService {

    private final CustomerDao customerDao = new CustomerDao();

    public List<Customer> getAllCustomer() {
        return customerDao.getAllCustomer();
    }

    public boolean addBusinessCustomer(Business business) {
        return customerDao.addBusinessCustomer(business);
    }

    public boolean addHouseHoldCustomer(HouseHold houseHold) {
        return customerDao.addHouseHoldCustomer(houseHold);
    }

    public List<Business> getAllBusiness() {
        return customerDao.getAllBusiness();
    }

    public boolean updateBusinessCustomer(Business business) {
        return customerDao.updateBusinessCustomer(business);
    }

    public boolean deleteBusinessCustomer(Long idDelete) {
        return customerDao.deleteBusinessCustomer(idDelete);
    }

    public List<HouseHold> getAllHouseHoldCustomer() {
        return customerDao.getAllHouseHoldCustomer();
    }

    public boolean deleteHouseHoldCustomer(Long idDelete) {
        return customerDao.deleteHouseHoldCustomer(idDelete);
    }

    public boolean updateHouseHoldCustomer(HouseHold houseHold) {
        return customerDao.updateHouseHoldCustomer(houseHold);
    }

    public Customer findCustomerById(Long id) {
        return customerDao.findCustomerById(id);
    }

    public List<Customer> findCustomer(String keyword) {
        return customerDao.findCustomer(keyword);
    }
}
