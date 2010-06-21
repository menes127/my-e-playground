<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/includes/taglibs.jsp"%>
<html>
  <head>
    <%@include file="/includes/meta.jsp"%>
    <title>create</title>
    <script type="text/javascript">
$(document).ready(function() {
    $("#userForm").validate();
});
    </script>
  </head>
  <body>
    <%@include file="/includes/header.jsp"%>
    <%@include file="/includes/error.jsp"%>
    <fieldset>
      <legend>Create User</legend>
      <form id="userForm" method="post" action="user.do?action=save">
        <table>
          <tr>
            <td><label for="username">Username:</label></td>
            <td><input id="username" type="text" name="username" value="" class="required"></td>
          </tr>
          <tr>
            <td><label for="password">Password:</label></td>
            <td><input id="password" type="password" name="password" value="" class="required" minlength="1"></td>
          </tr>
          <tr>
            <td><label for="confirmPassword">Confirm Password:</label></td>
            <td><input id="confirmPassword" type="password" name="confirmPassword" value="" class="required" equalTo="#password"></td>
          </tr>
          <tr>
            <td><label for="enabled">Enabled:</label></td>
            <td><input id="enabled" type="checkbox" name="enabled" checked></td>
          </tr>
          <tr>
            <td><label for="authority">Authorities:</label></td>
            <td><input id="authority" type="text" name="authority" value=""></td>
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