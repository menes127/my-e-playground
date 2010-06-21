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
    <%@include file="/includes/header.jsp"%>
    <fieldset>
      <legend>User Info</legend>
      <table>
        <tr>
          <td>Username:</td>
          <td>${user.username}</td>
        </tr>
        <tr>
          <td>Password:</td>
          <td>[PROTECTED]</td>
        </tr>
        <tr>
          <td>Enabled:</td>
          <td>${user.enabled}</td>
        </tr>
        <tr>
          <td>Authorities:</td>
          <td>${user.authority}</td>
        </tr>
      </table>
    </fieldset>
  </body>
</html>