package com.family168.springsecuritybook.ch401;

import org.springframework.context.ApplicationContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

import java.lang.reflect.Method;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserServlet extends HttpServlet {
    private UserManager userManager;

    private UserManager getUserManager() {
        if (userManager == null) {
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            userManager = (UserManager) ctx.getBean("userManager");
        }

        return userManager;
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

        try {
            Class clz = this.getClass();
            Method method = clz.getDeclaredMethod(action,
                    HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        List<UserBean> list = this.getUserManager().getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/user-list.jsp").forward(request, response);
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        request.getRequestDispatcher("/user-create.jsp")
               .forward(request, response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String username = request.getParameter("username");
        UserBean user = this.getUserManager().get(username);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/user-edit.jsp").forward(request, response);
    }

    public void view(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String username = request.getParameter("username");
        UserBean user = this.getUserManager().get(username);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/user-view.jsp").forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String enabled = request.getParameter("enabled");
        String authority = request.getParameter("authority");

        if (this.getUserManager().userExists(username)) {
            request.setAttribute("error", "user already exists.");
            request.getRequestDispatcher("user-create.jsp")
                   .forward(request, response);
        } else {
            this.getUserManager()
                .save(username, password, enabled != null, authority.split(","));
            request.getSession().setAttribute("message", "success");
            response.sendRedirect("user.do?action=list");
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String username = request.getParameter("username");
        String enabled = request.getParameter("enabled");
        String authority = request.getParameter("authority");
        this.getUserManager()
            .update(username, enabled != null, authority.split(","));
        request.getSession().setAttribute("message", "success");
        response.sendRedirect("user.do?action=list");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String username = request.getParameter("username");
        this.getUserManager().remove(username);
        request.getSession().setAttribute("message", "success");
        response.sendRedirect("user.do?action=list");
    }

    public void preChangePassword(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("user-changePassword.jsp")
               .forward(request, response);
    }

    public void changePassword(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        this.getUserManager().changePassword(oldPassword, newPassword);
        request.getSession().setAttribute("message", "success");
        response.sendRedirect("user.do?action=list");
    }
}
