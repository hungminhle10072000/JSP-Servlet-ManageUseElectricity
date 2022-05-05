package com.hdh.daos;

import com.hdh.models.Customer;
import com.hdh.models.Invoice;
import com.hdh.models.NoteBook;
import com.hdh.services.NoteBookService;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InvoiceDao {

    private final NoteBookService noteBookService = new NoteBookService();

    public boolean exportInvoice(int month, int year) {
        boolean checkExport = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Customer.class);
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

}
