<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>authentication</h3>
<table border="1">
  <tr>
    <td>&lt;sec:authentication property="name"/></td>
    <td><sec:authentication property="name"/></td>
  </tr>
  <tr>
    <td>
      &lt;sec:authentication property="authorities" var="authorities" scope="page"/><br>
      &lt;c:forEach items="\${authorities}" var="authority"><br>
        \${authority.authority}<br>
      &lt;/c:forEach>
    </td>
    <td>
      <sec:authentication property="principal.authorities" var="authorities" scope="page"/>
      <c:forEach items="${authorities}" var="authority">
        ${authority}
      </c:forEach>
    </td>
  </tr>
  <tr>
    <td>
      &lt;sec:authorize ifAllGranted="ROLE_ADMIN,ROLE_USER"><br>
        admin and user<br>
      &lt;/sec:authorize>
    </td>
    <td>
      <sec:authorize ifAllGranted="ROLE_ADMIN,ROLE_USER">
        admin and user
      </sec:authorize>
    </td>
  </tr>
  <tr>
    <td>
      &lt;sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER"><br>
        admin or user<br>
      &lt;/sec:authorize>
    </td>
    <td>
      <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
        admin or user
      </sec:authorize>
    </td>
  </tr>
  <tr>
    <td>
      &lt;sec:authorize ifNotGranted="ROLE_ADMIN"><br>
        not admin<br>
      &lt;/sec:authorize>
    </td>
    <td>
      <sec:authorize ifNotGranted="ROLE_ADMIN">
        not admin
      </sec:authorize>
    </td>
  </tr>
</table>
