package com.hdh.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hdh.dto.RequestAddBranch;
import com.hdh.models.Branch;
import com.hdh.services.BranchService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "branchController", value = "/branchController")
public class BranchController extends HttpServlet {

    private final BranchService branchService = new BranchService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Branch> branchList = branchService.listBranches();
        request.setAttribute("branchList", branchList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_branch/manage_branch_page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        RequestAddBranch requestAddBranch = gson.fromJson(reader, RequestAddBranch.class);
        Branch branchResult = branchService.addBranch(requestAddBranch);

        PrintWriter out = response.getWriter();
        JsonObject json = new JsonObject();

        if (branchResult == null) {
            response.setStatus(400);
            json.addProperty("Alert", "Failed");
            out.print(json);
        } else {
            response.setStatus(201);
            String branchJsonString = gson.toJson(branchResult);
            out.print(branchJsonString);
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
            branchService.deleteBranch(idDelete);
            resp.setStatus(201);
            json.addProperty("Alert", "Success");
            out.print(json);
        } catch (Exception e) {
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

        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Branch branchUpdate = gson.fromJson(reader, Branch.class);
        PrintWriter out = resp.getWriter();
        JsonObject json = new JsonObject();
        try {
            branchService.updateBranch(branchUpdate);
            resp.setStatus(201);
            json.addProperty("Alert", "Success");
            out.print(json);
        } catch (Exception e) {
            resp.setStatus(400);
            json.addProperty("Alert", "Failed");
            out.print(json);
        }
    }
}
