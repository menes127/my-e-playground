<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/includes/taglibs.jsp"%>
<html>
  <head>
    <%@ include file="/includes/meta.jsp"%>
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