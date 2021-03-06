package com.family168.springsecuritybook.ch302;

import org.springframework.context.ApplicationContext;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ContractServlet extends HttpServlet {
    private ContractService contractService;

    private ContractService getContractService() {
        if (contractService == null) {
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            contractService = (ContractService) ctx.getBean("contractService");
        }

        return contractService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        try {
            process(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        try {
            process(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    public void process(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            this.view(request, response);
        } else if ("list".equals(action)) {
            this.list(request, response);
        } else if ("save".equals(action)) {
            this.save(request, response);
        } else if ("update".equals(action)) {
            this.update(request, response);
        } else if ("remove".equals(action)) {
            this.remove(request, response);
        } else if ("create".equals(action)) {
            this.create(request, response);
        } else if ("edit".equals(action)) {
            this.edit(request, response);
        } else {
            System.out.println("Unkown Action: " + action);
        }
    }

    public void view(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        Contract contract = getContractService().get(id);
        request.setAttribute("contract", contract);
        request.getRequestDispatcher("/contract-view.jsp")
               .forward(request, response);
    }

    public void list(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        List<Contract> list = getContractService().getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/contract-list.jsp")
               .forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String content = request.getParameter("content");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                                                                     .getAuthentication()
                                                                     .getPrincipal();
        String username = userDetails.getUsername();
        getContractService().save(content, username);
        response.sendRedirect("contract.do?action=list");
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        String content = request.getParameter("content");
        getContractService().update(id, content);
        response.sendRedirect("contract.do?action=list");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        getContractService().removeById(id);
        response.sendRedirect("contract.do?action=list");
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        request.getRequestDispatcher("/contract-edit.jsp")
               .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        Contract contract = getContractService().get(id);
        request.setAttribute("contract", contract);
        request.getRequestDispatcher("/contract-edit.jsp")
               .forward(request, response);
    }
}
