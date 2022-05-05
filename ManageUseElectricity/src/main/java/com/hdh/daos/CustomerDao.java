package com.hdh.daos;

import com.hdh.models.*;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

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

    public Customer findCustomerById(Long id) {
        Customer customer;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Customer where id = :id");
            query.setParameter("id", id);
            customer = (Customer) query.uniqueResult();
            transaction.commit();
            return customer;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean updateHouseHoldCustomer(HouseHold houseHold) {
        boolean checkUpdate = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(houseHold);
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

    public boolean deleteBusinessCustomer(Long idBusiness) {

        boolean checkDelete = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria businessCriteria = session.createCriteria(Business.class);
            businessCriteria.add(Restrictions.eq("id", idBusiness));
            Business businessDelete;
            if (businessCriteria.list().size() > 0) {
                businessDelete = (Business) businessCriteria.list().get(0);
                session.delete(businessDelete);
                System.out.println("Delete business customer success !!!");
                transaction.commit();
                checkDelete = true;
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return checkDelete;
    }

    public List<HouseHold> getAllHouseHoldCustomer() {
        List<HouseHold> houseHoldList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(HouseHold.class);
            houseHoldList = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return houseHoldList;
    }

    public boolean deleteHouseHoldCustomer(Long idDelete) {

        boolean checkDelete = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(HouseHold.class);
            criteria.add(Restrictions.eq("id", idDelete));
            HouseHold houseHoldDelete;
            if (criteria.list().size() > 0) {
                houseHoldDelete = (HouseHold) criteria.list().get(0);
                session.delete(houseHoldDelete);
                System.out.println("Delete household customer success !!!");
                transaction.commit();
                checkDelete = true;
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return checkDelete;
    }

    public List<Customer> findCustomer(String keyword) {
        Long id = -1L;
        boolean checkConvertId = false;
        List<Customer> customerList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Customer.class);
            try {
                id = Long.valueOf(keyword);
                checkConvertId = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (checkConvertId) {
                criteria.add(Restrictions.disjunction()
                        .add(Restrictions.like("name", keyword, MatchMode.ANYWHERE))
                        .add(Restrictions.like("phoneNumber", keyword, MatchMode.ANYWHERE))
                        .add(Restrictions.eq("id", id))
                        .add(Restrictions.like("address", keyword, MatchMode.ANYWHERE)));
            } else {
                criteria.add(Restrictions.disjunction()
                        .add(Restrictions.like("name", keyword, MatchMode.ANYWHERE))
                        .add(Restrictions.like("phoneNumber", keyword, MatchMode.ANYWHERE))
                        .add(Restrictions.like("address", keyword, MatchMode.ANYWHERE)));
            }
            customerList = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerList;
    }

}
