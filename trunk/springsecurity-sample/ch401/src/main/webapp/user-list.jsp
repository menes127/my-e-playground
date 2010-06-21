<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>list</title>
  </head>
  <body>
    <%@include file="/includes/header.jsp"%>
    <%@include file="/includes/message.jsp"%>
    <table border="1" width="100%">
      <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Enabled</th>
        <th>Authorities</th>
        <th>Operation</th>
      </tr>
<c:forEach var="item" items="${list}">
      <tr>
        <td>${item.username}&nbsp;</td>
        <td>[PROTECTED]&nbsp;</td>
        <td>${item.enabled}&nbsp;</td>
        <td>${item.authority}&nbsp;</td>
        <td>
          <a href="user.do?action=view&username=${item.username}">View</a>
          |
          <a href="user.do?action=edit&username=${item.username}">Update</a>
          |
          <a href="user.do?action=remove&username=${item.username}">Remove</a>
        </td>
      </tr>
</c:forEach>
    </table>
  </body>
</html>