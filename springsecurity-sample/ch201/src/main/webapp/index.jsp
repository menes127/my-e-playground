<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>username : <sec:authentication property="name"/></div>
<hr>
<a href="admin.jsp">admin.jsp</a>
|
<a href="admin-method.jsp">admin-method.jsp</a>
|
<a href="user-method.jsp">user-method.jsp</a>
|
<a href="j_spring_security_logout">logout</a>

