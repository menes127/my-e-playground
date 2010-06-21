<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize ifAllGranted="ROLE_ADMIN">
  <%response.sendRedirect("admin.jsp");%>
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_ADMIN">
  <%response.sendRedirect("user.jsp");%>
</sec:authorize>
