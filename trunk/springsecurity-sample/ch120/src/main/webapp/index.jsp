<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
  <%response.sendRedirect("pluto/index.jsp");%>
</sec:authorize>
<%response.sendRedirect="spring_security_login"/>

