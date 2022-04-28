package com.hdh.daos;

import com.hdh.models.FormUse;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FormUseDao {

    public List<FormUse> getAllListFormUse() {
        List<FormUse> formUseList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria formUseCriteria = session.createCriteria(FormUse.class);
            formUseList = formUseCriteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return formUseList;
        }
    }
}
