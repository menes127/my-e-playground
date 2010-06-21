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
    <table width="100%">
      <tr>
        <td><a href="account.do?action=create">Create Account</a></td>
        <td align="right">
          username: <sec:authentication property="name"/>
          |
          <a href="j_spring_security_logout">logout</a>
        </td>
      </tr>
    </table>
    <hr>
    <table border="1" width="100%">
      <tr>
        <th>ID</th>
        <th>Message</th>
        <th>Owner</th>
        <th>Create Date</th>
        <th>Update Date</th>
        <th>Operation</th>
      </tr>
<c:forEach var="item" items="${list}">
      <tr>
        <td>${item.id}&nbsp;</td>
        <td>${item.content}&nbsp;</td>
        <td>${item.owner}&nbsp;</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.createDate}"/>&nbsp;</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.updateDate}"/>&nbsp;</td>
        <td>
          <a href="account.do?action=view&id=${item.id}">View</a>
          |
          <a href="account.do?action=edit&id=${item.id}">Update</a>
  <sec:accesscontrollist domainObject="${item}" hasPermission="8,16">
          |
          <a href="account.do?action=remove&id=${item.id}">Remove</a>
  </sec:accesscontrollist>
          |
          <a href="permission.do?action=list&id=${item.id}&clz=account">permission</a>
        </td>
      </tr>
</c:forEach>
    </table>
  </body>
</html>