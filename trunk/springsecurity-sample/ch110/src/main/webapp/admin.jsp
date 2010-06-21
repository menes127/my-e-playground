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
<a href="j_spring_security_switch_user?j_username=user">switch user</a>
|
<a href="j_spring_security_logout">logout</a>
