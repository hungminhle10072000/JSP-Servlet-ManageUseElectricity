package com.hdh.controllers;

import com.google.gson.Gson;
import com.hdh.models.Invoice;
import com.hdh.services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Integer.parseInt;

@WebServlet(name = "HomeController", value = "/HomeController")
public class HomeController extends HttpServlet {

    private final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int year = 0;
        List<Invoice> invoiceList = new ArrayList<>();

        ////Khoi tao gia tri theo thang va nam
        int value_t1 = 0;
        int value_t2 = 0;
        int value_t3 = 0;
        int value_t4 = 0;
        int value_t5 = 0;
        int value_t6 = 0;
        int value_t7 = 0;
        int value_t8 = 0;
        int value_t9 = 0;
        int value_t10 = 0;
        int value_t11 = 0;
        int value_t12 = 0;

        if (request.getParameter("yearFind") == null) {
            year = new java.util.Date().getYear() + 1900;
        } else {
            year = parseInt(request.getParameter("yearFind"));
        }
        invoiceList = invoiceService.findInvoiceByYear(year);

        for (Invoice invoice : invoiceList) {
            int monthWrite = invoice.getDateFrom().getMonth() + 1;
            switch (monthWrite) {
                case 1:
                    value_t1 += invoice.getTotalIndex();
                    break;
                case 2:
                    value_t2 += invoice.getTotalIndex();
                    break;
                case 3:
                    value_t3 += invoice.getTotalIndex();
                    break;
                case 4:
                    value_t4 += invoice.getTotalIndex();
                    break;
                case 5:
                    value_t5 += invoice.getTotalIndex();
                    break;
                case 6:
                    value_t6 += invoice.getTotalIndex();
                    break;
                case 7:
                    value_t7 += invoice.getTotalIndex();
                    break;
                case 8:
                    value_t8 += invoice.getTotalIndex();
                    break;
                case 9:
                    value_t9 += invoice.getTotalIndex();
                    break;
                case 10:
                    value_t10 += invoice.getTotalIndex();
                    break;
                case 11:
                    value_t11 += invoice.getTotalIndex();
                    break;
                case 12:
                    value_t12 += invoice.getTotalIndex();
                    break;
            }
        }

        String dataPoints;
        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        map = new HashMap<Object, Object>();
        map.put("label", "T1");
        map.put("y", value_t1);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T2");
        map.put("y", value_t2);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T3");
        map.put("y", value_t3);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T4");
        map.put("y", value_t4);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T5");
        map.put("y", value_t5);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T6");
        map.put("y", value_t6);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T7");
        map.put("y", value_t7);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T8");
        map.put("y", value_t8);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T9");
        map.put("y", value_t9);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T10");
        map.put("y", value_t10);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T11");
        map.put("y", value_t11);
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "T12");
        map.put("y", value_t12);
        list.add(map);
        dataPoints = gsonObj.toJson(list);

        ///Gan du lieu va tra ve
        session.setAttribute("dataPoints", dataPoints);
        session.setAttribute("year", year);
        request.getRequestDispatcher("/views/home_admin_page.jsp").forward(request, response);

    }
}
