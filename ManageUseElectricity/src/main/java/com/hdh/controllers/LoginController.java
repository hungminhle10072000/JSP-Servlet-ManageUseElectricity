package com.hdh.controllers;

import com.hdh.models.Branch;
import com.hdh.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Branch branch = new Branch();
        branch.setNameBranch("TMA lab 6");
        branch.setAddress("Quận 12");

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(branch);
        transaction.commit();
        session.close();

//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/login.jsp");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/home_login_page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
