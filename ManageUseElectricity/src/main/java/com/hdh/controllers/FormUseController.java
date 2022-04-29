package com.hdh.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hdh.dto.FormUseAdd;
import com.hdh.models.Branch;
import com.hdh.models.FormUse;
import com.hdh.services.FormUseService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FormUseController", value = "/FormUseController")
public class FormUseController extends HttpServlet {

    private final FormUseService formUseService = new FormUseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FormUse> formUseList = formUseService.listFormUses();
        request.setAttribute("formUseList", formUseList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_form_use/manage_form_use_page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        FormUseAdd formUseAdd = gson.fromJson(reader, FormUseAdd.class);
        FormUse formUseResult = formUseService.addFormUse(formUseAdd);

        PrintWriter out = response.getWriter();
        JsonObject json = new JsonObject();

        if (formUseResult == null) {
            response.setStatus(400);
            json.addProperty("Alert", "Failed");
            out.print(json);
        } else {
            response.setStatus(201);
            String formUseJsonString = gson.toJson(formUseResult);
            out.print(formUseJsonString);
            out.flush();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        JsonObject json = new JsonObject();
        try {
            Integer idDelete = Integer.parseInt(req.getReader().readLine());
            formUseService.deleteFormUses(idDelete);
            json.addProperty("Alert", "Success");
            out.print(json);
        } catch (Exception e) {
            json.addProperty("Alert", "Failed");
            out.print(json);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        FormUse formUseUpdate = gson.fromJson(reader, FormUse.class);
        PrintWriter out = resp.getWriter();
        JsonObject json = new JsonObject();
        if (formUseService.updateFormUse(formUseUpdate)) {
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
