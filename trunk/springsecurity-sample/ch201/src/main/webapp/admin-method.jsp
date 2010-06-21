<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.family168.springsecuritybook.ch201.MessageService"%>
<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
    MessageService messageService = (MessageService) ctx.getBean("messageService");
    out.println(messageService.adminMessage());
%>
