package com.hdh.daos;


import com.hdh.models.Customer;
import com.hdh.models.FormUse;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public void deleteFormUse(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            FormUse formUseDelete = session.get(FormUse.class, id);
            if (formUseDelete != null) {
                session.delete(formUseDelete);
                System.out.println("Delete form use success !!!");
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public FormUse addFormUse(FormUse formUse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        FormUse resultFormUseAdd = null;
        try {
            transaction = session.beginTransaction();
            Integer idFormUseAdd = (Integer) session.save(formUse);
            transaction.commit();
            resultFormUseAdd = session.get(FormUse.class, idFormUseAdd);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultFormUseAdd;
    }

    public boolean updateFormUse(FormUse formUse) {
        boolean checkResult = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(formUse);
            transaction.commit();
            checkResult = true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return checkResult;
    }

    public FormUse findFormUseById(Integer id) {
        FormUse formUse;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from FormUse where id = :id");
            query.setParameter("id", id);
            formUse = (FormUse) query.uniqueResult();
            transaction.commit();
            return formUse;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
