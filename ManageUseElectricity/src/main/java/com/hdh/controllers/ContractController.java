package com.hdh.controllers;

import com.google.gson.JsonObject;
import com.hdh.models.Branch;
import com.hdh.models.Contract;
import com.hdh.models.Customer;
import com.hdh.models.FormUse;
import com.hdh.services.BranchService;
import com.hdh.services.ContractService;
import com.hdh.services.CustomerService;
import com.hdh.services.FormUseService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ContractController", value = "/ContractController")
public class ContractController extends HttpServlet {

    private final CustomerService customerService = new CustomerService();
    private final BranchService branchService = new BranchService();
    private final FormUseService formUseService = new FormUseService();
    private final ContractService contractService = new ContractService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String typePage = request.getParameter("typePage");
        if (typePage != null && typePage.equals("addContractPage")) {
            List<Customer> customerList = customerService.getAllCustomer();
            List<Branch> branchList = branchService.listBranches();
            List<FormUse> formUseList = formUseService.listFormUses();
            request.setAttribute("customerList", customerList);
            request.setAttribute("branchList", branchList);
            request.setAttribute("formUseList", formUseList);
            requestDispatcher = request.getRequestDispatcher("/views/view_contract/add_contract_page.jsp");
        } else if (typePage != null && typePage.equals("detailContractPage")) {
            Long idDetail = Long.parseLong(request.getParameter("idDetail"));
            Contract contractDetail = contractService.findContractById(idDetail);
            List<Customer> customerList = customerService.getAllCustomer();
            List<Branch> branchList = branchService.listBranches();
            List<FormUse> formUseList = formUseService.listFormUses();
            request.setAttribute("customerList", customerList);
            request.setAttribute("branchList", branchList);
            request.setAttribute("formUseList", formUseList);
            request.setAttribute("contractDetail", contractDetail);
            requestDispatcher = request.getRequestDispatcher("/views/view_contract/detail_contract_page.jsp");
        } else {
            List<Contract> contractList = contractService.getAllContract();
            request.setAttribute("contractList", contractList);
            requestDispatcher = request.getRequestDispatcher("/views/view_contract/manage_contract_page.jsp");
        }
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        List<Customer> customerList = customerService.getAllCustomer();
        List<Branch> branchList = branchService.listBranches();
        List<FormUse> formUseList = formUseService.listFormUses();

        String contentContract = request.getParameter("contentContract");
        String sDate = request.getParameter("dateSign");
        Long idCustomer = Long.valueOf(request.getParameter("selectCustomer"));
        Integer idBranch = Integer.valueOf(request.getParameter("selectBranch"));
        Integer idFormUse = Integer.valueOf(request.getParameter("selectFormUse"));
        Branch branch = branchService.findBranchById(idBranch);
        FormUse formUse = formUseService.findFormUseById(idFormUse);
        Customer customer = customerService.findCustomerById(idCustomer);

        String typePostContract = request.getParameter("typePostContract");
        if (typePostContract != null && typePostContract.equals("update")) {
            Long idContractUpdate = Long.valueOf(request.getParameter("idContract"));
            try {
                Date dateSign = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

                Contract contractUpdate = contractService.findContractById(idContractUpdate);
                contractUpdate.setContent(contentContract);
                contractUpdate.setDateSign(dateSign);
                contractUpdate.setBranch(branch);
                contractUpdate.setFormUse(formUse);
                contractUpdate.setCustomer(customer);

                if (contractService.updateContract(contractUpdate)) {
                    Contract contractUpdateReusult = contractService.findContractById(idContractUpdate);
                    request.setAttribute("contractDetail", contractUpdateReusult);
                    request.setAttribute("alert", "Update success");
                } else {
                    request.setAttribute("contractDetail", contractUpdate);
                    request.setAttribute("alert", "Update failed");
                }
            } catch (Exception e) {
                Contract contractUpdate = contractService.findContractById(idContractUpdate);
                request.setAttribute("contractDetail", contractUpdate);
                request.setAttribute("alert", "Update failed");
                e.printStackTrace();
            }
            request.setAttribute("customerList", customerList);
            request.setAttribute("branchList", branchList);
            request.setAttribute("formUseList", formUseList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_contract/detail_contract_page.jsp");
            requestDispatcher.forward(request, response);
        } else {
            try {
                Date dateSign = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

                Contract contractAdd = new Contract();
                contractAdd.setContent(contentContract);
                contractAdd.setDateSign(dateSign);
                contractAdd.setBranch(branch);
                contractAdd.setFormUse(formUse);

                Contract contractCheck = contractService.findContractByCustomer(idCustomer);
                if (contractCheck != null) {
                    request.setAttribute("alert", "Exists contract with customer id: " + idCustomer);
                } else {
                    contractAdd.setCustomer(customer);
                    contractService.addContract(contractAdd);
                    request.setAttribute("alert", "Add Success");
                }
            } catch (Exception e) {
                request.setAttribute("alert", "Add failed");
                e.printStackTrace();
            }
            request.setAttribute("customerList", customerList);
            request.setAttribute("branchList", branchList);
            request.setAttribute("formUseList", formUseList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_contract/add_contract_page.jsp");
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

        if (contractService.deleteContract(idDelete)) {
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
