package com.hdh.daos;

import com.hdh.models.Contract;
import com.hdh.models.Customer;
import com.hdh.models.NoteBook;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class NoteBookDao {
    private final CustomerDao customerDao = new CustomerDao();


    public List<NoteBook> getAllNoteBookCustomer(Long idCustomer) {

        List<NoteBook> noteBookList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from NoteBook As u where u.electricMeter.contract.customer.id = :idCustomer");
            query.setParameter("idCustomer", idCustomer);
            System.out.println(query.list().size());
            noteBookList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return noteBookList;
    }

    public NoteBook findNoteBookByMonthYearCustomer(int month, int year, Long idCustomer) {
        NoteBook noteBook = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Customer customer = customerDao.findCustomerById(idCustomer);
            List<NoteBook> noteBookListCustomer = getAllNoteBookCustomer(idCustomer);
            int monthCheck;
            int yearCheck;
            for (NoteBook item : noteBookListCustomer) {
                monthCheck = item.getDateWrite().getMonth() + 1;
                yearCheck = item.getDateWrite().getYear() + 1900;
                if (customer.equals(item.getElectricMeter().getContract().getCustomer()) && month == monthCheck && year == yearCheck) {
                    noteBook = item;
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return noteBook;
    }

    public boolean addNoteBook(NoteBook noteBookAdd) {
        boolean checkAdd = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(noteBookAdd);
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

    public List<NoteBook> getAllNoteBook() {
        List<NoteBook> noteBookList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from NoteBook");
            noteBookList = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return noteBookList;
    }

    public boolean deleteNoteBook(Long idDelete) {
        boolean checkDelete = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            NoteBook noteBookDelete = session.get(NoteBook.class, idDelete);
            if (noteBookDelete != null) {
                session.delete(noteBookDelete);
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

    public NoteBook findNoteBookById(Long idNoteBook) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        NoteBook noteBook = session.get(NoteBook.class, idNoteBook);
        return noteBook;
    }

    public boolean updateNoteBook(NoteBook noteBookUpdate) {
        boolean checkUpdate = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(noteBookUpdate);
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
