<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>view</title>
  </head>
  <body>
    <table width="100%">
      <tr>
        <td><a href="message.do?action=list">Back</a></td>
        <td align="right">
          username: <sec:authentication property="name"/>
          |
          <a href="j_spring_security_logout">logout</a>
        </td>
      </tr>
    </table>
    <hr>
    <fieldset>
      <legend>Contract Info</legend>
      <table>
        <tr>
          <td>ID:</td>
          <td>${contract.id}</td>
        </tr>
        <tr>
          <td>Message:</td>
          <td>${contract.content}</td>
        </tr>
        <tr>
          <td>Owner:</td>
          <td>${contract.owner}</td>
        </tr>
        <tr>
          <td>Create Date:</td>
          <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${contract.createDate}"/></td>
        </tr>
        <tr>
          <td>Update Date:</td>
          <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${contract.updateDate}"/></td>
        </tr>
      </table>
    </fieldset>
  </body>
</html>