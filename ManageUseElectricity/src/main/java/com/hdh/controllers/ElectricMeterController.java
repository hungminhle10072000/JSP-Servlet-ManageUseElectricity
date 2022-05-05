package com.hdh.controllers;

import com.google.gson.JsonObject;
import com.hdh.models.*;
import com.hdh.services.ContractService;
import com.hdh.services.ElectricMeterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ElectricMeterController", value = "/ElectricMeterController")
public class ElectricMeterController extends HttpServlet {

    private final ContractService contractService = new ContractService();
    private final ElectricMeterService electricMeterService = new ElectricMeterService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String typePage = request.getParameter("typePage");
        if (typePage != null && typePage.equals("addElectricPage")) {
            List<Contract> contractList = contractService.getAllContract();
            request.setAttribute("contractList", contractList);
            requestDispatcher = request.getRequestDispatcher("/views/view_electricmeter/add_electricmeter_page.jsp");
        } else if (typePage != null && typePage.equals("detailElectricPage")) {
            Long idDetail = Long.parseLong(request.getParameter("idDetail"));
            ElectricMeter electricMeter = electricMeterService.findElectricMeterById(idDetail);
            request.setAttribute("electricDetail", electricMeter);
            List<Contract> contractList = contractService.getAllContract();
            request.setAttribute("contractList", contractList);
            requestDispatcher = request.getRequestDispatcher("/views/view_electricmeter/detail_electricmeter_page.jsp");
        } else {
            List<ElectricMeter> electricMeterList;
            String keyWord = request.getParameter("keyWord");

            if (keyWord != null) {
                electricMeterList = electricMeterService.findElectric(keyWord);
            } else {
                electricMeterList = electricMeterService.getAllElectricMeter();
            }
            request.setAttribute("keyWord", keyWord);
            request.setAttribute("electricMeterList", electricMeterList);
            requestDispatcher = request.getRequestDispatcher("/views/view_electricmeter/manage_electricmeter_page.jsp");
        }
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String typeElectricMeter = request.getParameter("typeElectricMeter");
        Long idContract = Long.valueOf(request.getParameter("selectContract"));

        Contract contract = contractService.findContractById(idContract);

        String typePostElectric = request.getParameter("typePostElectric");
        List<Contract> contractList = contractService.getAllContract();

        if (typePostElectric != null && typePostElectric.equals("update")) {
            Long idUpdate = Long.valueOf(request.getParameter("idElectric"));
            ElectricMeter electricMeterUpdate = electricMeterService.findElectricMeterById(idUpdate);
            electricMeterUpdate.setTypeElectricMeter(typeElectricMeter);
            electricMeterUpdate.setContract(contract);

            if (electricMeterService.updateElectric(electricMeterUpdate)) {
                ElectricMeter electricMeterResult = electricMeterService.findElectricMeterById(idUpdate);
                request.setAttribute("electricDetail", electricMeterResult);
                request.setAttribute("alert", "Update success");
            } else {
                request.setAttribute("electricDetail", electricMeterUpdate);
                request.setAttribute("alert", "Update failed");
            }
            request.setAttribute("contractList", contractList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_electricmeter/detail_electricmeter_page.jsp");
            requestDispatcher.forward(request, response);
        } else {
            ElectricMeter electricMeterCheckExist = electricMeterService.findElectricMeterByContract(idContract);
            if (electricMeterCheckExist != null) {
                request.setAttribute("alert", "This contract already has a electric meter!!!");
            } else {
                ElectricMeter electricMeter = new ElectricMeter();
                electricMeter.setContract(contract);
                electricMeter.setTypeElectricMeter(typeElectricMeter);
                if (electricMeterService.addElectricMeter(electricMeter)) {
                    request.setAttribute("alert", "Add Success");
                } else {
                    request.setAttribute("alert", "Add failed");
                }
            }
            request.setAttribute("contractList", contractList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_electricmeter/add_electricmeter_page.jsp");
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

        if (electricMeterService.deleteElectric(idDelete)) {
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
