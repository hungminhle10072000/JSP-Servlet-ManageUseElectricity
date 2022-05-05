package com.hdh.controllers;

import com.hdh.models.Customer;
import com.hdh.services.CustomerService;
import com.hdh.services.InvoiceService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InvoiceController", value = "/InvoiceController")
public class InvoiceController extends HttpServlet {

    private final InvoiceService invoiceService = new InvoiceService();
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        int yearExport = 0;
        int monthExport = 0;
        Long idCustomer = 0L;
        String typePage = request.getParameter("typePage");
        if (request.getParameter("selectCustomer") != null) {
            idCustomer = Long.valueOf(request.getParameter("selectCustomer"));
        }
        if (request.getParameter("yearExport") != null) {
            yearExport = Integer.parseInt(request.getParameter("yearExport"));
        }
        if (request.getParameter("monthExport") != null) {
            monthExport = Integer.parseInt(request.getParameter("monthExport"));
        }
        if (typePage != null && typePage.equals("exportPage")) {
            if (yearExport != 0 && monthExport != 0) {
                if (invoiceService.exportInvoice(monthExport, yearExport)) {
                    request.setAttribute("alert", "Export Success !!!");
                } else {
                    request.setAttribute("alert", "Export Failed !!!");
                }
            }
            requestDispatcher = request.getRequestDispatcher("/views/view_invoice/export_invoice_page.jsp");
        } else {
            requestDispatcher = request.getRequestDispatcher("/views/view_invoice/manage_invoice_page.jsp");
        }
        request.setAttribute("customerList", customerService.getAllCustomer());
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
