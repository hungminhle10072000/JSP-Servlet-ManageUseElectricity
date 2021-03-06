package com.hdh.daos;


import com.hdh.models.Branch;
import com.hdh.models.Contract;
import com.hdh.models.ElectricMeter;
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

public class ContractDao {

    public boolean addContract(Contract contract) {
        boolean checkAdd = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(contract);
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

    public List<Contract> getAllContract() {
        List<Contract> contractList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Contract.class);
            contractList = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return contractList;
        }
    }

    public boolean deleteContract(Long id) {
        boolean checkDelete = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
//            String hql = "DELETE FROM Contract As u WHERE u.id = :id ";
//            Query query = session.createQuery(hql);
//            query.setParameter("id", id);
//            int result = query.executeUpdate();
//            checkDelete = true;
            Contract contractDelete = session.get(Contract.class, id);
            if (contractDelete != null) {
                session.delete(contractDelete);
                System.out.println("Delete contract success !!!");
            }
            transaction.commit();
            checkDelete = true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return checkDelete;
    }

    public Contract findContractById(Long id) {
        Contract contract = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Contract  where id = :id");
            query.setParameter("id", id);
            contract = (Contract) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return contract;
    }

    public boolean updateContract(Contract contractUpdate) {
        boolean checkUpdate = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(contractUpdate);
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

    public Contract findContractByCustomer(Long idCustomer) {
        Contract contract = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Contract As u where u.customer.id = :id");
            query.setParameter("id", idCustomer);
            contract = (Contract) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return contract;
    }

    public List<Contract> findContract(String keyword) {
        Long id = -1L;
        List<Contract> contractList;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Contract.class)
                    .createAlias("customer", "c")
                    .createAlias("formUse", "f")
                    .createAlias("branch", "b");
            try {
                id = Long.valueOf(keyword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            criteria.add(
                    Restrictions.disjunction()
                            .add(Restrictions.eq("id", id))
                            .add(Restrictions.eq("c.name", keyword))
                            .add(Restrictions.eq("b.nameBranch", keyword))
                            .add(Restrictions.eq("f.nameForm", keyword))
                            .add(Restrictions.like("content", keyword, MatchMode.ANYWHERE)));
            contractList = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        return contractList;
    }
}
