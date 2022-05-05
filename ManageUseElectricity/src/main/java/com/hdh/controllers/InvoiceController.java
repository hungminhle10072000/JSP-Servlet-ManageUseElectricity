package com.hdh.controllers;

import com.google.gson.JsonObject;
import com.hdh.models.Customer;
import com.hdh.services.CustomerService;
import com.hdh.services.InvoiceService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

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
        if (typePage != null && typePage.equals("exportPage")) {
            if (request.getParameter("yearExport") != null) {
                yearExport = Integer.parseInt(request.getParameter("yearExport"));
            }
            if (request.getParameter("monthExport") != null) {
                monthExport = Integer.parseInt(request.getParameter("monthExport"));
            }
            if (request.getParameter("selectCustomer") != null) {
                idCustomer = Long.valueOf(request.getParameter("selectCustomer"));
            }
            if (yearExport != 0 && monthExport != 0) {
                if (invoiceService.exportInvoice(monthExport, yearExport, idCustomer)) {
                    request.setAttribute("alert", "Export Success !!!");
                } else {
                    request.setAttribute("alert", "Export Failed !!!");
                }
            }
            requestDispatcher = request.getRequestDispatcher("/views/view_invoice/export_invoice_page.jsp");
        } else {
            int monthFind = 0;
            int yearFind = 0;
            if (request.getParameter("monthFind") == null || request.getParameter("yearFind") == null) {
                monthFind = new java.util.Date().getMonth();
                yearFind = new java.util.Date().getYear() + 1900;
                if (new java.util.Date().getMonth() + 1 == 1) {
                    monthFind = 12;
                    yearFind = new java.util.Date().getYear() + 1900 - 1;
                }
                request.setAttribute("invoiceList", invoiceService.findInvoice(monthFind, yearFind, -1L));
            } else {
                monthFind = Integer.valueOf(request.getParameter("monthFind"));
                yearFind = Integer.valueOf(request.getParameter("yearFind"));
                Long idCustomerFind = Long.valueOf(request.getParameter("selectCustomer"));
                if (idCustomerFind == 0) {
                    request.setAttribute("invoiceList", invoiceService.findInvoice(monthFind, yearFind, -1L));
                } else {
                    request.setAttribute("invoiceList", invoiceService.findInvoice(monthFind, yearFind, idCustomerFind));
                    request.setAttribute("idFind", idCustomerFind);
                }
            }
            request.setAttribute("monthFind", monthFind);
            request.setAttribute("yearFind", yearFind);
            requestDispatcher = request.getRequestDispatcher("/views/view_invoice/manage_invoice_page.jsp");
        }
        request.setAttribute("customerList", customerService.getAllCustomer());
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        JsonObject json = new JsonObject();

        Long idDelete = Long.parseLong(req.getReader().readLine());

        if (invoiceService.deleteInvoice(idDelete)) {
            resp.setStatus(200);
            json.addProperty("Alert", "Success");
            out.print(json);
        } else {
            resp.setStatus(400);
            json.addProperty("Alert", "Failed");
            out.print(json);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        JsonObject json = new JsonObject();

        Long idUpdate = Long.parseLong(req.getReader().readLine());
        if (invoiceService.updateInvoice(idUpdate)) {
            resp.setStatus(200);
            json.addProperty("Alert", "Success");
            out.print(json);
        } else {
            resp.setStatus(400);
            json.addProperty("Alert", "Failed");
            out.print(json);
        }
    }
}
