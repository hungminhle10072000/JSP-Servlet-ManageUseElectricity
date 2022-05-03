package com.hdh.daos;

import com.hdh.models.Business;
import com.hdh.models.Contract;
import com.hdh.models.ElectricMeter;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ElectricMeterDao {

    public boolean addElectricMeter(ElectricMeter electricMeter) {
        boolean checkAdd = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(electricMeter);
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

    public ElectricMeter findElectricMeterByContract(Long idContract) {
        ElectricMeter electricMeter = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ElectricMeter As u where u.contract.id = :idContract");
            query.setParameter("idContract", idContract);
            electricMeter = (ElectricMeter) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return electricMeter;
    }

    public List<ElectricMeter> getAllElectricMeter() {
        List<ElectricMeter> electricMeterList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(ElectricMeter.class);
            electricMeterList = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return electricMeterList;
    }

    public boolean deleteElectricMeter(Long idDelete) {

        boolean checkDelete = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM ElectricMeter As u WHERE u.id = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", idDelete);
            int result = query.executeUpdate();
            checkDelete = true;
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return checkDelete;
    }

    public ElectricMeter findElectricMeterById(Long id) {
        ElectricMeter electricMeter = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ElectricMeter  where id = :id");
            query.setParameter("id", id);
            electricMeter = (ElectricMeter) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return electricMeter;
    }

    public boolean updateElectric(ElectricMeter electricMeter) {
        boolean checkUpdate = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(electricMeter);
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
