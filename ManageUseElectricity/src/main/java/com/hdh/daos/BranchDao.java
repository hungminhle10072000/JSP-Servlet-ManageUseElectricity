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

}
