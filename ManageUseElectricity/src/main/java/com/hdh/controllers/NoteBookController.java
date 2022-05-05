package com.hdh.controllers;

import com.google.gson.JsonObject;
import com.hdh.models.Customer;
import com.hdh.models.ElectricMeter;
import com.hdh.models.NoteBook;
import com.hdh.services.CustomerService;
import com.hdh.services.ElectricMeterService;
import com.hdh.services.NoteBookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "NoteBookController", value = "/NoteBookController")
public class NoteBookController extends HttpServlet {

    private final ElectricMeterService electricMeterService = new ElectricMeterService();
    private final NoteBookService noteBookService = new NoteBookService();

    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        String typePage = request.getParameter("typePage");

        if (typePage != null && typePage.equals("addNoteBookPage")) {
            List<ElectricMeter> electricMeterList = electricMeterService.getAllElectricMeter();
            request.setAttribute("electricMeterList", electricMeterList);
            requestDispatcher = request.getRequestDispatcher("/views/view_notebook/add_notebook_page.jsp");
            requestDispatcher.forward(request, response);
        } else if (typePage != null && typePage.equals("detailNoteBookPage")) {
            List<ElectricMeter> electricMeterList = electricMeterService.getAllElectricMeter();
            request.setAttribute("electricMeterList", electricMeterList);
            Long id = Long.valueOf(request.getParameter("idDetail"));
            request.setAttribute("detailNoteBook", noteBookService.findNoteBookById(id));
            requestDispatcher = request.getRequestDispatcher("/views/view_notebook/detail_notebook_page.jsp");
            requestDispatcher.forward(request, response);

        } else if (request.getParameter("searchForm") != null) {
            List<Customer> customerList = customerService.getAllCustomer();
            int monthFind = Integer.parseInt(request.getParameter("monthFind"));
            int yearFind = Integer.parseInt(request.getParameter("yearFind"));
            Long idFind = 0L;
            if (request.getParameter("selectCustomer") != null) {
                idFind = Long.valueOf(request.getParameter("selectCustomer"));
            }
            List<NoteBook> noteBookList = noteBookService.findNoteBook(monthFind, yearFind, idFind);
            request.setAttribute("monthFind", monthFind);
            request.setAttribute("yearFind", yearFind);
            request.setAttribute("idFind", idFind);
            request.setAttribute("noteBookList", noteBookList);
            request.setAttribute("customerList", customerList);
            requestDispatcher = request.getRequestDispatcher("/views/view_notebook/manage_notebook_page.jsp");
            requestDispatcher.forward(request, response);
        } else {
            List<Customer> customerList = customerService.getAllCustomer();
            List<NoteBook> noteBookList = noteBookService.getAllNoteBook();
            request.setAttribute("noteBookList", noteBookList);
            request.setAttribute("customerList", customerList);
            requestDispatcher = request.getRequestDispatcher("/views/view_notebook/manage_notebook_page.jsp");
            requestDispatcher.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        RequestDispatcher requestDispatcher;
        List<ElectricMeter> electricMeterList = electricMeterService.getAllElectricMeter();
        request.setAttribute("electricMeterList", electricMeterList);
        String typePost = request.getParameter("typePost");
        String sDateWrite = request.getParameter("dateWrite");
        Double indexElectric = Double.valueOf(request.getParameter("indexElectric"));
        Long selectElectric = Long.valueOf(request.getParameter("selectElectric"));

        ElectricMeter electricMeter = electricMeterService.findElectricMeterById(selectElectric);

        if (typePost != null && typePost.equals("update")) {

            Long idUpdate = Long.valueOf(request.getParameter("idUpdate"));
            NoteBook noteBookUpdate = noteBookService.findNoteBookById(idUpdate);

            try {
                Date dateWrite = new SimpleDateFormat("yyyy-MM-dd").parse(sDateWrite);
                int month = dateWrite.getMonth() + 1;
                int year = dateWrite.getYear() + 1900;
                NoteBook noteBookLastMonth = noteBookService.findNoteBookByMonthYearCustomer(month - 1, year, electricMeter.getContract().getCustomer().getId());
                if (noteBookLastMonth != null) {
                    if (noteBookLastMonth.getIndexElectric() > indexElectric) {
                        request.setAttribute("alert", "Request re-enter value of index electric meter !!!");
                        requestDispatcher = request.getRequestDispatcher("/views/view_notebook/add_notebook_page.jsp");
                        requestDispatcher.forward(request, response);
                        return;
                    }
                }
                noteBookUpdate.setIndexElectric(indexElectric);
                noteBookUpdate.setElectricMeter(electricMeter);
                noteBookUpdate.setDateWrite(dateWrite);
                if (noteBookService.updateNoteBookService(noteBookUpdate)) {
                    request.setAttribute("detailNoteBook", noteBookUpdate);
                    request.setAttribute("alert", "Update Success !!!");
                } else {
                    request.setAttribute("detailNoteBook", noteBookService.findNoteBookById(idUpdate));
                    request.setAttribute("alert", "Update Failed !!!");
                }
                requestDispatcher = request.getRequestDispatcher("/views/view_notebook/detail_notebook_page.jsp");
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Date dateWrite = new SimpleDateFormat("yyyy-MM-dd").parse(sDateWrite);
                int month = dateWrite.getMonth() + 1;
                int year = dateWrite.getYear() + 1900;
                NoteBook noteBookLastMonth = noteBookService.findNoteBookByMonthYearCustomer(month - 1, year, electricMeter.getContract().getCustomer().getId());
                NoteBook noteBookCurrent = noteBookService.findNoteBookByMonthYearCustomer(month, year, electricMeter.getContract().getCustomer().getId());
                if (noteBookLastMonth != null) {
                    if (noteBookLastMonth.getIndexElectric() > indexElectric) {
                        request.setAttribute("alert", "Request re-enter value of index electric meter !!!");
                        requestDispatcher = request.getRequestDispatcher("/views/view_notebook/add_notebook_page.jsp");
                        requestDispatcher.forward(request, response);
                        return;
                    }
                }
                if (noteBookCurrent != null) {
                    noteBookCurrent.setDateWrite(dateWrite);
                    noteBookCurrent.setIndexElectric(indexElectric);
                    noteBookCurrent.setElectricMeter(electricMeter);
                    noteBookService.updateNoteBookService(noteBookCurrent);
                    request.setAttribute("alert", "Add Success !!!");
                } else {
                    NoteBook noteBookAdd = new NoteBook();
                    noteBookAdd.setIndexElectric(indexElectric);
                    noteBookAdd.setElectricMeter(electricMeter);
                    noteBookAdd.setDateWrite(dateWrite);
                    if (noteBookService.addNoteBook(noteBookAdd)) {
                        request.setAttribute("alert", "Add Success !!!");
                    } else {
                        request.setAttribute("alert", "Add Failed !!!");
                    }
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            requestDispatcher = request.getRequestDispatcher("/views/view_notebook/add_notebook_page.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        JsonObject json = new JsonObject();

        Long idDelete = Long.parseLong(req.getReader().readLine());

        if (noteBookService.deleteNoteBook(idDelete)) {
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
