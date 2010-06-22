package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {
	private String greeting;

	public void init() {
		// greeting = getInitParameter("greeting");
		// greeting = getServletConfig().getInitParameter("greeting");
		greeting = "Hello";
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String welcomeInfo = greeting + ", " + "高明华menes127";
		
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>");
		out.println("Welcome Page");
		out.println("</title></head>");
		out.println("<body>");
		out.println(welcomeInfo);
		out.println("</body></html>");
		out.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}