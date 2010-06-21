package com.family168.springsecuritybook.ch303;

import org.springframework.context.ApplicationContext;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MessageServlet extends HttpServlet {
    private MessageService messageService;

    private MessageService getMessageService() {
        if (messageService == null) {
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            messageService = (MessageService) ctx.getBean("messageService");
        }

        return messageService;
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
        Message message = getMessageService().get(id);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message-view.jsp")
               .forward(request, response);
    }

    public void list(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        List<Message> list = getMessageService().getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/message-list.jsp")
               .forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String messageContent = request.getParameter("message");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                                                                     .getAuthentication()
                                                                     .getPrincipal();
        String username = userDetails.getUsername();

        //getMessageService().save(message, username);
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMessage(messageContent);
        message.setOwner(username);
        message.setCreateDate(new Date());
        message.setUpdateDate(new Date());
        getMessageService().save(message);
        response.sendRedirect("message.do?action=list");
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        String message = request.getParameter("message");
        getMessageService().update(id, message);
        response.sendRedirect("message.do?action=list");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        getMessageService().removeById(id);
        response.sendRedirect("message.do?action=list");
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        request.getRequestDispatcher("/message-edit.jsp")
               .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Long id = Long.valueOf(request.getParameter("id"));
        Message message = getMessageService().get(id);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message-edit.jsp")
               .forward(request, response);
    }
}
