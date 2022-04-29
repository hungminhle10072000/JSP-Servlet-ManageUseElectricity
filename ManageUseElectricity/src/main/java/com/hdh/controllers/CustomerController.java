package com.hdh.controllers;

import com.hdh.models.Business;
import com.hdh.models.Customer;
import com.hdh.models.HouseHold;
import com.hdh.services.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/CustomerController")
public class CustomerController extends HttpServlet {

    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Customer> customerList = customerService.getAllCustomer();
        request.setAttribute("customerList", customerList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_customer/manage_customer_page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        boolean result = false;
        String typeCustomer = request.getParameter("typeCustomer");

        if (typeCustomer.equals("household")) {
            HouseHold houseHold = new HouseHold();
            houseHold.setName(request.getParameter("nameHouse"));
            houseHold.setAddress(request.getParameter("address"));
            houseHold.setPhoneNumber(request.getParameter("phone-number"));
            houseHold.setIndentityCard(request.getParameter("indentity-card"));
            result = customerService.addHouseHoldCustomer(houseHold);
        }
        if (typeCustomer.equals("business")) {
            Business business = new Business();
            business.setName(request.getParameter("nameBusiness"));
            business.setAddress(request.getParameter("address"));
            business.setPhoneNumber(request.getParameter("phone-number"));
            business.setTaxCode(request.getParameter("tax-code"));
            result = customerService.addBusinessCustomer(business);
        }

        RequestDispatcher requestDispatcher;

        if (result == true) {
            doGet(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher("/views/view_customer/add_customer_page.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
