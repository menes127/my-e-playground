<%@page import="org.springframework.security.GrantedAuthority"%>
<%@page import="org.springframework.security.context.SecurityContext"%>
<%@page import="org.springframework.security.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.userdetails.UserDetails"%>
admin.jsp
<%
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();
    GrantedAuthority[] authorities = userDetails.getAuthorities();
%>
