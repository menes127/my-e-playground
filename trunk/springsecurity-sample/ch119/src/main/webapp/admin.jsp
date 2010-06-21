<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.family168.springsecuritybook.ch119.HelloService"%>
<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
    HelloService service = (HelloService) ctx.getBean("helloServiceProxy");
    String result = service.hello("family168");
    out.println(result);
%>
