package com.hdh.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hdh.models.Business;
import com.hdh.services.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BusinessCustomerController", value = "/BusinessCustomerController")
public class BusinessCustomerController extends HttpServlet {

    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listBusinessCustomer", customerService.getAllBusiness());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_customer/manage_business_page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Business businessUpdate = gson.fromJson(reader, Business.class);
        PrintWriter out = resp.getWriter();
        JsonObject json = new JsonObject();

        if (customerService.updateBusinessCustomer(businessUpdate)) {
            resp.setStatus(201);
            json.addProperty("Alert", "Success");
            out.print(json);
        } else {
            resp.setStatus(400);
            json.addProperty("Alert", "Failed");
            out.print(json);
        }
    }
}
