<%@page import="org.springframework.security.core.GrantedAuthority"%>
<%@page import="org.springframework.security.core.context.SecurityContext"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>

admin.jsp<hr/>
<%
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();
	out.println("userDetails="+userDetails + "<hr/>");
	out.println("userDetails.getAuthorities()="+userDetails.getAuthorities() + "<hr/>");
    //GrantedAuthority[] authorities = userDetails.getAuthorities();
%>
