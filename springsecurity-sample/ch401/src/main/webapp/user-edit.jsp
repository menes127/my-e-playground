<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>edit</title>
  </head>
  <body>
    <%@include file="/includes/header.jsp"%>
    <%@include file="/includes/error.jsp"%>
    <fieldset>
      <legend>Edit User</legend>
      <form id="userForm" method="post" action="user.do?action=update">
        <table>
          <tr>
            <td><label for="username">Username:</label></td>
            <td><input type="hidden" name="username" value="${user.username}">${user.username}&nbsp;</td>
          </tr>
          <tr>
            <td><label for="enabled">Enabled:</label></td>
            <td><input id="enabled" type="checkbox" name="enabled" ${user.enabled ? 'checked' : ''}></td>
          </tr>
          <tr>
            <td><label for="authority">Authorities:</label></td>
            <td><input id="authority" type="text" name="authority" value="${user.authority}"></td>
          </tr>
          <tr>
            <td colspan="2">
              <input type="submit">
              <input type="reset">
            </td>
          </tr>
        </table>
      </form>
    </fieldset>
  </body>
</html>