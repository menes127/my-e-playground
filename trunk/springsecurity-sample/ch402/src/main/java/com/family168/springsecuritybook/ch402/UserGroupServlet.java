package com.family168.springsecuritybook.ch402;

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


public class UserGroupServlet extends HttpServlet {
    private UserGroupManager userGroupManager;

    private UserGroupManager getUserGroupManager() {
        if (userGroupManager == null) {
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            userGroupManager = (UserGroupManager) ctx.getBean(
                    "userGroupManager");
        }

        return userGroupManager;
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
        List<UserGroupBean> list = this.getUserGroupManager().getAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/group-list.jsp")
               .forward(request, response);
    }

    public void create(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        request.getRequestDispatcher("/group-create.jsp")
               .forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String name = request.getParameter("name");
        String authority = request.getParameter("authority");

        this.getUserGroupManager().save(name, authority.split(","));
        request.getSession().setAttribute("message", "success");
        response.sendRedirect("group.do?action=list");
    }

    public void view(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String groupName = request.getParameter("name");
        UserGroupBean bean = this.getUserGroupManager().get(groupName);
        request.setAttribute("group", bean);
        request.getRequestDispatcher("/group-view.jsp")
               .forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String groupName = request.getParameter("name");
        this.getUserGroupManager().remove(groupName);
        request.getSession().setAttribute("message", "success");
        response.sendRedirect("group.do?action=list");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String groupName = request.getParameter("name");
        UserGroupBean bean = this.getUserGroupManager().get(groupName);
        request.setAttribute("group", bean);
        request.getRequestDispatcher("/group-edit.jsp")
               .forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String oldName = request.getParameter("oldName");
        String groupName = request.getParameter("name");
        String member = request.getParameter("member");
        String authority = request.getParameter("authority");
        this.getUserGroupManager()
            .update(oldName, groupName, member.split(","), authority.split(","));
        request.getSession().setAttribute("message", "success");
        response.sendRedirect("group.do?action=list");
    }
}
