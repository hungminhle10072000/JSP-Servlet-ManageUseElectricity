package com.hdh.daos;

import com.hdh.models.Business;
import com.hdh.models.Customer;
import com.hdh.models.FormUse;
import com.hdh.models.HouseHold;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria formUseCriteria = session.createCriteria(Customer.class);
            customerList = formUseCriteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerList;
    }

    public boolean addBusinessCustomer(Business business) {
        boolean checkAdd = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(business);
            transaction.commit();
            checkAdd = true;

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return checkAdd;
    }

    public boolean addHouseHoldCustomer(HouseHold houseHold) {
        boolean checkAdd = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            session.save(houseHold);
            transaction.commit();
            checkAdd = true;

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return checkAdd;
    }

    public List<Business> getAllBusiness() {
        List<Business> businessList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Business.class);
            businessList = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return businessList;
    }

    public boolean updateBusinessCustomer(Business business) {
        boolean checkUpdate = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(business);
            transaction.commit();
            checkUpdate = true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return checkUpdate;
    }

}
