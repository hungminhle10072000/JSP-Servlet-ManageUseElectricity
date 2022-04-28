package com.hdh.controllers;

import com.hdh.models.FormUse;
import com.hdh.services.FormUseService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FormUseController", value = "/FormUseController")
public class FormUseController extends HttpServlet {

    private FormUseService formUseService = new FormUseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FormUse> formUseList = formUseService.listFormUses();
        request.setAttribute("formUseList", formUseList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_form_use/manage_form_use_page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
