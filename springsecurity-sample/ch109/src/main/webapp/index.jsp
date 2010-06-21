<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
  username : <sec:authentication property="name"/>
  |
  authorities: <sec:authentication property="authorities" var="authorities" scope="page"/>
<c:forEach items="${authorities}" var="authority">
  ${authority.authority}
</c:forEach>
</div>
<hr>
<a href="admin.jsp">admin.jsp</a>
