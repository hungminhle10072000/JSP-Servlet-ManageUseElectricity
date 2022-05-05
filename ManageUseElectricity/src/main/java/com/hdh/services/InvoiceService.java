package com.hdh.services;

import com.hdh.daos.InvoiceDao;

public class InvoiceService {

    private final InvoiceDao invoiceDao = new InvoiceDao();

    public boolean exportInvoice(int month, int year) {
        return invoiceDao.exportInvoice(month, year);
    }
}
