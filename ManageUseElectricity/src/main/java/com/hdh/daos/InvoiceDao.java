package com.hdh.daos;

import com.hdh.models.*;
import com.hdh.services.NoteBookService;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDao {

    private final NoteBookService noteBookService = new NoteBookService();

    public boolean exportInvoice(int month, int year, Long idCustomer) {
        boolean checkExport = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Customer.class);
            if (idCustomer != 0) {
                criteria.add(Restrictions.eq("id", idCustomer));
            }
            List<Customer> customerList = criteria.list();
            for (Object item : customerList) {
                Invoice invoiceAdd = new Invoice();
                Customer customer = (Customer) item;
                NoteBook noteBookCurrent = noteBookService.findNoteBookByMonthYearCustomer(month + 1, year, customer.getId());
                NoteBook noteBookLast = noteBookService.findNoteBookByMonthYearCustomer(month, year, customer.getId());
                if (noteBookCurrent != null && noteBookLast != null) {
                    Double indexUse = noteBookCurrent.getIndexElectric() - noteBookLast.getIndexElectric();
                    invoiceAdd.setDateFrom(noteBookLast.getDateWrite());
                    invoiceAdd.setDateEnd(noteBookCurrent.getDateWrite());
                    invoiceAdd.setTotalIndex(indexUse);
                    invoiceAdd.setTotalMoney(indexUse * noteBookCurrent.getElectricMeter().getContract().getFormUse().getUnitPrice());
                    invoiceAdd.setElectricMeter(noteBookCurrent.getElectricMeter());
                    System.out.println(invoiceAdd.getElectricMeter().getId());
                    session.save(invoiceAdd);
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            checkExport = false;
        } finally {
            session.close();
        }
        return checkExport;
    }

    public List<Invoice> findInvoice(int month, int year, Long idCustomer) {
        List<Invoice> invoices = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Invoice.class)
                    .createAlias("electricMeter.contract.customer", "c");
            if (idCustomer != -1L) {
                criteria.add(Restrictions.eq("c.id", idCustomer));
            }
            List<Invoice> temp = criteria.list();
            for (Object item : temp) {
                Invoice invoiceTemp = (Invoice) item;
                int monthCheck = invoiceTemp.getDateFrom().getMonth() + 1;
                int yearCheck = invoiceTemp.getDateFrom().getYear() + 1900;
                if (monthCheck == month && yearCheck == year) {
                    invoices.add(invoiceTemp);
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return invoices;
    }

    public boolean deleteInvoice(Long idDelete) {
        boolean checkDelete = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Invoice As u WHERE u.id = :id ";
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

    public boolean updateInvoice(Long idInvoice) {
        boolean checkUpdate = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Invoice where id = :idInvoice");
            query.setParameter("idInvoice", idInvoice);
            Invoice invoice = (Invoice) query.uniqueResult();
            invoice.setStatusPaid(!invoice.isStatusPaid());
            session.update(invoice);
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

    public List<Invoice> findInvoiceByYear(int year) {
        List<Invoice> invoices = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Invoice.class)
                    .createAlias("electricMeter.contract.customer", "c");
            List<Invoice> temp = criteria.list();
            for (Object item : temp) {
                Invoice invoiceTemp = (Invoice) item;
                int yearCheck = invoiceTemp.getDateFrom().getYear() + 1900;
                if (yearCheck == year) {
                    invoices.add(invoiceTemp);
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return invoices;
    }

}
