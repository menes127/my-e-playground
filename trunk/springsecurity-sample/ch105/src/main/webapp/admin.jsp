<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1>admin</h1>
<div>username : <sec:authentication property="name"/></div>
<hr>
<a href="j_spring_security_logout">logout</a>

<%@include file="include.jsp"%>
