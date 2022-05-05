package com.hdh.services;

import com.hdh.daos.InvoiceDao;
import com.hdh.models.Invoice;

import java.util.List;

public class InvoiceService {

    private final InvoiceDao invoiceDao = new InvoiceDao();

    public boolean exportInvoice(int month, int year, Long idCustomer) {
        return invoiceDao.exportInvoice(month, year, idCustomer);
    }

    public List<Invoice> findInvoice(int month, int year, Long idCustomer) {
        return invoiceDao.findInvoice(month, year, idCustomer);
    }

    public boolean deleteInvoice(Long idDelete) {
        return invoiceDao.deleteInvoice(idDelete);
    }

    public boolean updateInvoice(Long idInvoice) {
        return invoiceDao.updateInvoice(idInvoice);
    }

    public List<Invoice> findInvoiceByYear(int Year) {
        return invoiceDao.findInvoiceByYear(Year);
    }
}
