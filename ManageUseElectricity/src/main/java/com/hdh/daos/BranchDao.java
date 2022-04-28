package com.hdh.daos;

import com.hdh.models.Branch;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchDao {

    public List<Branch> getllListBranchs() {
        List<Branch> listBranchResult = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria branchCriteria = session.createCriteria(Branch.class);
            listBranchResult = branchCriteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listBranchResult;
    }

    public void deleteBranch(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Branch branchDelete = session.get(Branch.class, id);
            if (branchDelete != null) {
                session.delete(branchDelete);
                System.out.println("Delete branch success !!!");
            }
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateBranch(Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(branch);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Branch addBranch(Branch branch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Branch resultBranchAdd = null;
        try {
            transaction = session.beginTransaction();
            Integer idBranchAdd = (Integer) session.save(branch);
            transaction.commit();
            resultBranchAdd = session.get(Branch.class, idBranchAdd);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return resultBranchAdd;
        }
    }

}
